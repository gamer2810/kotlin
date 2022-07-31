/*
 * Copyright 2010-2015 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.jetbrains.kotlin.kdoc.lexer;

import com.intellij.lang.ASTNode;
import com.intellij.lang.PsiBuilder;
import com.intellij.lang.PsiBuilderFactory;
import com.intellij.lang.PsiParser;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.intellij.psi.tree.ILazyParseableElementType;
import com.intellij.psi.tree.TokenSet;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.kotlin.idea.KotlinLanguage;
import org.jetbrains.kotlin.kdoc.parser.KDocLinkParser;
import org.jetbrains.kotlin.kdoc.parser.KDocParser;
import org.jetbrains.kotlin.kdoc.psi.impl.KDocImpl;

public interface KDocTokens {
    ILazyParseableElementType KDOC = new ILazyParseableElementType("KDoc", KotlinLanguage.INSTANCE) {
        @Override
        public ASTNode parseContents(ASTNode chameleon) {
            PsiElement parentElement = chameleon.getTreeParent().getPsi();
            Project project = parentElement.getProject();
            PsiBuilder builder = PsiBuilderFactory.getInstance().createBuilder(project, chameleon, new KDocLexer(), getLanguage(),
                                                                               chameleon.getText());
            PsiParser parser = new KDocParser();

            return parser.parse(this, builder).getFirstChildNode();
        }

        @Nullable
        @Override
        public ASTNode createNode(CharSequence text) {
            return new KDocImpl(text);
        }
    };

    int START_Type = 149;
    int END_Type = 150;
    int LEADING_ASTERISK_Type = 151;
    int TEXT_Type = 152;
    int CODE_BLOCK_TEXT_Type = 153;
    int TAG_NAME_Type = 154;
    int MARKDOWN_ESCAPED_CHAR_Type = 155;
    int MARKDOWN_INLINE_LINK_Type = 156;

    KDocToken START                 = new KDocToken("KDOC_START", START_Type);
    KDocToken END                   = new KDocToken("KDOC_END", END_Type);
    KDocToken LEADING_ASTERISK      = new KDocToken("KDOC_LEADING_ASTERISK", LEADING_ASTERISK_Type);

    KDocToken TEXT                  = new KDocToken("KDOC_TEXT", TEXT_Type);
    KDocToken CODE_BLOCK_TEXT       = new KDocToken("KDOC_CODE_BLOCK_TEXT", CODE_BLOCK_TEXT_Type);

    KDocToken TAG_NAME              = new KDocToken("KDOC_TAG_NAME", TAG_NAME_Type);
    ILazyParseableElementType MARKDOWN_LINK = new ILazyParseableElementType("KDOC_MARKDOWN_LINK", KotlinLanguage.INSTANCE) {
        @Override
        public ASTNode parseContents(ASTNode chameleon) {
            return KDocLinkParser.parseMarkdownLink(this, chameleon);
        }
    };

    KDocToken MARKDOWN_ESCAPED_CHAR = new KDocToken("KDOC_MARKDOWN_ESCAPED_CHAR", MARKDOWN_ESCAPED_CHAR_Type);
    KDocToken MARKDOWN_INLINE_LINK = new KDocToken("KDOC_MARKDOWN_INLINE_LINK", MARKDOWN_INLINE_LINK_Type);

    TokenSet KDOC_HIGHLIGHT_TOKENS = TokenSet.create(START, END, LEADING_ASTERISK, TEXT, CODE_BLOCK_TEXT, MARKDOWN_LINK, MARKDOWN_ESCAPED_CHAR, MARKDOWN_INLINE_LINK);
    TokenSet CONTENT_TOKENS = TokenSet.create(TEXT, CODE_BLOCK_TEXT, TAG_NAME, MARKDOWN_LINK, MARKDOWN_ESCAPED_CHAR, MARKDOWN_INLINE_LINK);
}
