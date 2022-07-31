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

package org.jetbrains.kotlin.lexer;

import com.intellij.psi.TokenType;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.TokenSet;
import org.jetbrains.kotlin.kdoc.lexer.KDocTokens;
import org.jetbrains.kotlin.psi.KtPsiUtil;

import static org.jetbrains.kotlin.KtNodeTypes.DOT_QUALIFIED_EXPRESSION;
import static org.jetbrains.kotlin.KtNodeTypes.SAFE_ACCESS_EXPRESSION;

public interface KtTokens {
    int INVALID_Type = 0;
    int EOF_Type = 1;
    int RESERVED_Type = 2;
    int BLOCK_COMMENT_Type = 3;
    int EOL_COMMENT_Type = 4;
    int SHEBANG_COMMENT_Type = 5;
    int INTEGER_LITERAL_Type = 6;
    int FLOAT_LITERAL_Type = 7;
    int CHARACTER_LITERAL_Type = 8;
    int CLOSING_QUOTE_Type = 9;
    int OPEN_QUOTE_Type = 10;
    int REGULAR_STRING_PART_Type = 11;
    int ESCAPE_SEQUENCE_Type = 12;
    int SHORT_TEMPLATE_ENTRY_START_Type = 13;
    int LONG_TEMPLATE_ENTRY_START_Type = 14;
    int LONG_TEMPLATE_ENTRY_END_Type = 15;
    int DANGLING_NEWLINE_Type = 16;
    int PACKAGE_KEYWORD_Type = 17;
    int AS_KEYWORD_Type = 18;
    int TYPE_ALIAS_KEYWORD_Type = 19;
    int CLASS_KEYWORD_Type = 20;
    int THIS_KEYWORD_Type = 21;
    int SUPER_KEYWORD_Type = 22;
    int VAL_KEYWORD_Type = 23;
    int VAR_KEYWORD_Type = 24;
    int FUN_KEYWORD_Type = 25;
    int FOR_KEYWORD_Type = 26;
    int NULL_KEYWORD_Type = 27;
    int TRUE_KEYWORD_Type = 28;
    int FALSE_KEYWORD_Type = 29;
    int IS_KEYWORD_Type = 30;
    int IN_KEYWORD_Type = 31;
    int THROW_KEYWORD_Type = 32;
    int RETURN_KEYWORD_Type = 33;
    int BREAK_KEYWORD_Type = 34;
    int CONTINUE_KEYWORD_Type = 35;
    int OBJECT_KEYWORD_Type = 36;
    int IF_KEYWORD_Type = 37;
    int TRY_KEYWORD_Type = 38;
    int ELSE_KEYWORD_Type = 39;
    int WHILE_KEYWORD_Type = 40;
    int DO_KEYWORD_Type = 41;
    int WHEN_KEYWORD_Type = 42;
    int INTERFACE_KEYWORD_Type = 43;
    int TYPEOF_KEYWORD_Type = 44;
    int AS_SAFE_Type = 45;
    int IDENTIFIER_Type = 46;
    int FIELD_IDENTIFIER_Type = 47;
    int LBRACKET_Type = 48;
    int RBRACKET_Type = 49;
    int LBRACE_Type = 50;
    int RBRACE_Type = 51;
    int LPAR_Type = 52;
    int RPAR_Type = 53;
    int DOT_Type = 54;
    int PLUSPLUS_Type = 55;
    int MINUSMINUS_Type = 56;
    int MUL_Type = 57;
    int PLUS_Type = 58;
    int MINUS_Type = 59;
    int EXCL_Type = 60;
    int DIV_Type = 61;
    int PERC_Type = 62;
    int LT_Type = 63;
    int GT_Type = 64;
    int LTEQ_Type = 65;
    int GTEQ_Type = 66;
    int EQEQEQ_Type = 67;
    int ARROW_Type = 68;
    int DOUBLE_ARROW_Type = 69;
    int EXCLEQEQEQ_Type = 70;
    int EQEQ_Type = 71;
    int EXCLEQ_Type = 72;
    int EXCLEXCL_Type = 73;
    int ANDAND_Type = 74;
    int AND_Type = 75;
    int OROR_Type = 76;
    int SAFE_ACCESS_Type = 77;
    int ELVIS_Type = 78;
    int QUEST_Type = 79;
    int COLONCOLON_Type = 80;
    int COLON_Type = 81;
    int SEMICOLON_Type = 82;
    int DOUBLE_SEMICOLON_Type = 83;
    int RANGE_Type = 84;
    int RANGE_UNTIL_Type = 85;
    int EQ_Type = 86;
    int MULTEQ_Type = 87;
    int DIVEQ_Type = 88;
    int PERCEQ_Type = 89;
    int PLUSEQ_Type = 90;
    int MINUSEQ_Type = 91;
    int NOT_IN_Type = 92;
    int NOT_IS_Type = 93;
    int HASH_Type = 94;
    int AT_Type = 95;
    int COMMA_Type = 96;
    int EOL_OR_SEMICOLON_Type = 97;
    int FILE_KEYWORD_Type = 98;
    int FIELD_KEYWORD_Type = 99;
    int PROPERTY_KEYWORD_Type = 100;
    int RECEIVER_KEYWORD_Type = 101;
    int PARAM_KEYWORD_Type = 102;
    int SETPARAM_KEYWORD_Type = 103;
    int DELEGATE_KEYWORD_Type = 104;
    int IMPORT_KEYWORD_Type = 105;
    int WHERE_KEYWORD_Type = 106;
    int BY_KEYWORD_Type = 107;
    int GET_KEYWORD_Type = 108;
    int SET_KEYWORD_Type = 109;
    int CONSTRUCTOR_KEYWORD_Type = 110;
    int INIT_KEYWORD_Type = 111;
    int CONTEXT_KEYWORD_Type = 112;
    int ABSTRACT_KEYWORD_Type = 113;
    int ENUM_KEYWORD_Type = 114;
    int CONTRACT_KEYWORD_Type = 115;
    int OPEN_KEYWORD_Type = 116;
    int INNER_KEYWORD_Type = 117;
    int OVERRIDE_KEYWORD_Type = 118;
    int PRIVATE_KEYWORD_Type = 119;
    int PUBLIC_KEYWORD_Type = 120;
    int INTERNAL_KEYWORD_Type = 121;
    int PROTECTED_KEYWORD_Type = 122;
    int CATCH_KEYWORD_Type = 123;
    int OUT_KEYWORD_Type = 124;
    int VARARG_KEYWORD_Type = 125;
    int REIFIED_KEYWORD_Type = 126;
    int DYNAMIC_KEYWORD_Type = 127;
    int COMPANION_KEYWORD_Type = 128;
    int SEALED_KEYWORD_Type = 129;
    int FINALLY_KEYWORD_Type = 130;
    int FINAL_KEYWORD_Type = 131;
    int LATEINIT_KEYWORD_Type = 132;
    int DATA_KEYWORD_Type = 133;
    int VALUE_KEYWORD_Type = 134;
    int INLINE_KEYWORD_Type = 135;
    int NOINLINE_KEYWORD_Type = 136;
    int TAILREC_KEYWORD_Type = 137;
    int EXTERNAL_KEYWORD_Type = 138;
    int ANNOTATION_KEYWORD_Type = 139;
    int CROSSINLINE_KEYWORD_Type = 140;
    int OPERATOR_KEYWORD_Type = 141;
    int INFIX_KEYWORD_Type = 142;
    int CONST_KEYWORD_Type = 143;
    int SUSPEND_KEYWORD_Type = 144;
    int HEADER_KEYWORD_Type = 145;
    int IMPL_KEYWORD_Type = 146;
    int EXPECT_KEYWORD_Type = 147;
    int ACTUAL_KEYWORD_Type = 148;

    KtToken EOF   = new KtToken("EOF", EOF_Type);

    KtToken RESERVED    = new KtToken("RESERVED", RESERVED_Type);

    KtToken BLOCK_COMMENT     = new KtToken("BLOCK_COMMENT", BLOCK_COMMENT_Type);
    KtToken EOL_COMMENT       = new KtToken("EOL_COMMENT", EOL_COMMENT_Type);
    KtToken SHEBANG_COMMENT   = new KtToken("SHEBANG_COMMENT", SHEBANG_COMMENT_Type);

    IElementType DOC_COMMENT   = KDocTokens.KDOC;

    IElementType WHITE_SPACE = TokenType.WHITE_SPACE;

    KtToken INTEGER_LITERAL    = new KtToken("INTEGER_LITERAL", INTEGER_LITERAL_Type);
    KtToken FLOAT_LITERAL      = new KtToken("FLOAT_CONSTANT", FLOAT_LITERAL_Type);
    KtToken CHARACTER_LITERAL  = new KtToken("CHARACTER_LITERAL", CHARACTER_LITERAL_Type);

    KtToken CLOSING_QUOTE = new KtToken("CLOSING_QUOTE", CLOSING_QUOTE_Type);
    KtToken OPEN_QUOTE = new KtToken("OPEN_QUOTE", OPEN_QUOTE_Type);
    KtToken REGULAR_STRING_PART = new KtToken("REGULAR_STRING_PART", REGULAR_STRING_PART_Type);
    KtToken ESCAPE_SEQUENCE = new KtToken("ESCAPE_SEQUENCE", ESCAPE_SEQUENCE_Type);
    KtToken SHORT_TEMPLATE_ENTRY_START = new KtToken("SHORT_TEMPLATE_ENTRY_START", SHORT_TEMPLATE_ENTRY_START_Type);
    KtToken LONG_TEMPLATE_ENTRY_START = new KtToken("LONG_TEMPLATE_ENTRY_START", LONG_TEMPLATE_ENTRY_START_Type);
    KtToken LONG_TEMPLATE_ENTRY_END = new KtToken("LONG_TEMPLATE_ENTRY_END", LONG_TEMPLATE_ENTRY_END_Type);
    KtToken DANGLING_NEWLINE = new KtToken("DANGLING_NEWLINE", DANGLING_NEWLINE_Type);

    KtKeywordToken PACKAGE_KEYWORD          = KtKeywordToken.keyword("package", PACKAGE_KEYWORD_Type);
    KtKeywordToken AS_KEYWORD               = KtKeywordToken.keyword("as", AS_KEYWORD_Type);
    KtKeywordToken TYPE_ALIAS_KEYWORD       = KtKeywordToken.keyword("typealias", TYPE_ALIAS_KEYWORD_Type);
    KtKeywordToken CLASS_KEYWORD            = KtKeywordToken.keyword("class", CLASS_KEYWORD_Type);
    KtKeywordToken THIS_KEYWORD             = KtKeywordToken.keyword("this", THIS_KEYWORD_Type);
    KtKeywordToken SUPER_KEYWORD            = KtKeywordToken.keyword("super", SUPER_KEYWORD_Type);
    KtKeywordToken VAL_KEYWORD              = KtKeywordToken.keyword("val", VAL_KEYWORD_Type);
    KtKeywordToken VAR_KEYWORD              = KtKeywordToken.keyword("var", VAR_KEYWORD_Type);
    KtModifierKeywordToken FUN_KEYWORD      = KtModifierKeywordToken.keywordModifier("fun", FUN_KEYWORD_Type);
    KtKeywordToken FOR_KEYWORD              = KtKeywordToken.keyword("for", FOR_KEYWORD_Type);
    KtKeywordToken NULL_KEYWORD             = KtKeywordToken.keyword("null", NULL_KEYWORD_Type);
    KtKeywordToken TRUE_KEYWORD             = KtKeywordToken.keyword("true", TRUE_KEYWORD_Type);
    KtKeywordToken FALSE_KEYWORD            = KtKeywordToken.keyword("false", FALSE_KEYWORD_Type);
    KtKeywordToken IS_KEYWORD               = KtKeywordToken.keyword("is", IS_KEYWORD_Type);
    KtModifierKeywordToken IN_KEYWORD       = KtModifierKeywordToken.keywordModifier("in", IN_KEYWORD_Type);
    KtKeywordToken THROW_KEYWORD            = KtKeywordToken.keyword("throw", THROW_KEYWORD_Type);
    KtKeywordToken RETURN_KEYWORD           = KtKeywordToken.keyword("return", RETURN_KEYWORD_Type);
    KtKeywordToken BREAK_KEYWORD            = KtKeywordToken.keyword("break", BREAK_KEYWORD_Type);
    KtKeywordToken CONTINUE_KEYWORD         = KtKeywordToken.keyword("continue", CONTINUE_KEYWORD_Type);
    KtKeywordToken OBJECT_KEYWORD           = KtKeywordToken.keyword("object", OBJECT_KEYWORD_Type);
    KtKeywordToken IF_KEYWORD               = KtKeywordToken.keyword("if", IF_KEYWORD_Type);
    KtKeywordToken TRY_KEYWORD              = KtKeywordToken.keyword("try", TRY_KEYWORD_Type);
    KtKeywordToken ELSE_KEYWORD             = KtKeywordToken.keyword("else", ELSE_KEYWORD_Type);
    KtKeywordToken WHILE_KEYWORD            = KtKeywordToken.keyword("while", WHILE_KEYWORD_Type);
    KtKeywordToken DO_KEYWORD               = KtKeywordToken.keyword("do", DO_KEYWORD_Type);
    KtKeywordToken WHEN_KEYWORD             = KtKeywordToken.keyword("when", WHEN_KEYWORD_Type);
    KtKeywordToken INTERFACE_KEYWORD        = KtKeywordToken.keyword("interface", INTERFACE_KEYWORD_Type);

    // Reserved for future use:
    KtKeywordToken TYPEOF_KEYWORD           = KtKeywordToken.keyword("typeof", TYPEOF_KEYWORD_Type);

    KtToken AS_SAFE = KtKeywordToken.keyword("AS_SAFE", AS_SAFE_Type);

    KtToken IDENTIFIER = new KtToken("IDENTIFIER", IDENTIFIER_Type);

    KtToken FIELD_IDENTIFIER = new KtToken("FIELD_IDENTIFIER", FIELD_IDENTIFIER_Type);
    KtSingleValueToken LBRACKET    = new KtSingleValueToken("LBRACKET", "[", LBRACKET_Type);
    KtSingleValueToken RBRACKET    = new KtSingleValueToken("RBRACKET", "]", RBRACKET_Type);
    KtSingleValueToken LBRACE      = new KtSingleValueToken("LBRACE", "{", LBRACE_Type);
    KtSingleValueToken RBRACE      = new KtSingleValueToken("RBRACE", "}", RBRACE_Type);
    KtSingleValueToken LPAR        = new KtSingleValueToken("LPAR", "(", LPAR_Type);
    KtSingleValueToken RPAR        = new KtSingleValueToken("RPAR", ")", RPAR_Type);
    KtSingleValueToken DOT         = new KtSingleValueToken("DOT", ".", DOT_Type);
    KtSingleValueToken PLUSPLUS    = new KtSingleValueToken("PLUSPLUS", "++", PLUSPLUS_Type);
    KtSingleValueToken MINUSMINUS  = new KtSingleValueToken("MINUSMINUS", "--", MINUSMINUS_Type);
    KtSingleValueToken MUL         = new KtSingleValueToken("MUL", "*", MUL_Type);
    KtSingleValueToken PLUS        = new KtSingleValueToken("PLUS", "+", PLUS_Type);
    KtSingleValueToken MINUS       = new KtSingleValueToken("MINUS", "-", MINUS_Type);
    KtSingleValueToken EXCL        = new KtSingleValueToken("EXCL", "!", EXCL_Type);
    KtSingleValueToken DIV         = new KtSingleValueToken("DIV", "/", DIV_Type);
    KtSingleValueToken PERC        = new KtSingleValueToken("PERC", "%", PERC_Type);
    KtSingleValueToken LT          = new KtSingleValueToken("LT", "<", LT_Type);
    KtSingleValueToken GT          = new KtSingleValueToken("GT", ">", GT_Type);
    KtSingleValueToken LTEQ        = new KtSingleValueToken("LTEQ", "<=", LTEQ_Type);
    KtSingleValueToken GTEQ        = new KtSingleValueToken("GTEQ", ">=", GTEQ_Type);
    KtSingleValueToken EQEQEQ      = new KtSingleValueToken("EQEQEQ", "===", EQEQEQ_Type);
    KtSingleValueToken ARROW       = new KtSingleValueToken("ARROW", "->", ARROW_Type);
    KtSingleValueToken DOUBLE_ARROW       = new KtSingleValueToken("DOUBLE_ARROW", "=>", DOUBLE_ARROW_Type);
    KtSingleValueToken EXCLEQEQEQ  = new KtSingleValueToken("EXCLEQEQEQ", "!==", EXCLEQEQEQ_Type);
    KtSingleValueToken EQEQ        = new KtSingleValueToken("EQEQ", "==", EQEQ_Type);
    KtSingleValueToken EXCLEQ      = new KtSingleValueToken("EXCLEQ", "!=", EXCLEQ_Type);
    KtSingleValueToken EXCLEXCL    = new KtSingleValueToken("EXCLEXCL", "!!", EXCLEXCL_Type);
    KtSingleValueToken ANDAND      = new KtSingleValueToken("ANDAND", "&&", ANDAND_Type);
    KtSingleValueToken AND         = new KtSingleValueToken("AND", "&", AND_Type);
    KtSingleValueToken OROR        = new KtSingleValueToken("OROR", "||", OROR_Type);
    KtSingleValueToken SAFE_ACCESS = new KtSingleValueToken("SAFE_ACCESS", "?.", SAFE_ACCESS_Type);
    KtSingleValueToken ELVIS       = new KtSingleValueToken("ELVIS", "?:", ELVIS_Type);
    KtSingleValueToken QUEST       = new KtSingleValueToken("QUEST", "?", QUEST_Type);
    KtSingleValueToken COLONCOLON  = new KtSingleValueToken("COLONCOLON", "::", COLONCOLON_Type);
    KtSingleValueToken COLON       = new KtSingleValueToken("COLON", ":", COLON_Type);
    KtSingleValueToken SEMICOLON   = new KtSingleValueToken("SEMICOLON", ";", SEMICOLON_Type);
    KtSingleValueToken DOUBLE_SEMICOLON   = new KtSingleValueToken("DOUBLE_SEMICOLON", ";;", DOUBLE_SEMICOLON_Type);
    KtSingleValueToken RANGE       = new KtSingleValueToken("RANGE", "..", RANGE_Type);
    KtSingleValueToken RANGE_UNTIL       = new KtSingleValueToken("RANGE_UNTIL", "..<", RANGE_UNTIL_Type);
    KtSingleValueToken EQ          = new KtSingleValueToken("EQ", "=", EQ_Type);
    KtSingleValueToken MULTEQ      = new KtSingleValueToken("MULTEQ", "*=", MULTEQ_Type);
    KtSingleValueToken DIVEQ       = new KtSingleValueToken("DIVEQ", "/=", DIVEQ_Type);
    KtSingleValueToken PERCEQ      = new KtSingleValueToken("PERCEQ", "%=", PERCEQ_Type);
    KtSingleValueToken PLUSEQ      = new KtSingleValueToken("PLUSEQ", "+=", PLUSEQ_Type);
    KtSingleValueToken MINUSEQ     = new KtSingleValueToken("MINUSEQ", "-=", MINUSEQ_Type);
    KtKeywordToken NOT_IN      = KtKeywordToken.keyword("NOT_IN", "!in", NOT_IN_Type);
    KtKeywordToken NOT_IS      = KtKeywordToken.keyword("NOT_IS", "!is", NOT_IS_Type);
    KtSingleValueToken HASH        = new KtSingleValueToken("HASH", "#", HASH_Type);
    KtSingleValueToken AT          = new KtSingleValueToken("AT", "@", AT_Type);

    KtSingleValueToken COMMA       = new KtSingleValueToken("COMMA", ",", COMMA_Type);

    KtToken EOL_OR_SEMICOLON   = new KtToken("EOL_OR_SEMICOLON", EOL_OR_SEMICOLON_Type);
    KtKeywordToken FILE_KEYWORD    = KtKeywordToken.softKeyword("file", FILE_KEYWORD_Type);
    KtKeywordToken FIELD_KEYWORD     = KtKeywordToken.softKeyword("field", FIELD_KEYWORD_Type);
    KtKeywordToken PROPERTY_KEYWORD     = KtKeywordToken.softKeyword("property", PROPERTY_KEYWORD_Type);
    KtKeywordToken RECEIVER_KEYWORD     = KtKeywordToken.softKeyword("receiver", RECEIVER_KEYWORD_Type);
    KtKeywordToken PARAM_KEYWORD     = KtKeywordToken.softKeyword("param", PARAM_KEYWORD_Type);
    KtKeywordToken SETPARAM_KEYWORD  = KtKeywordToken.softKeyword("setparam", SETPARAM_KEYWORD_Type);
    KtKeywordToken DELEGATE_KEYWORD  = KtKeywordToken.softKeyword("delegate", DELEGATE_KEYWORD_Type);
    KtKeywordToken IMPORT_KEYWORD    = KtKeywordToken.softKeyword("import", IMPORT_KEYWORD_Type);
    KtKeywordToken WHERE_KEYWORD     = KtKeywordToken.softKeyword("where", WHERE_KEYWORD_Type);
    KtKeywordToken BY_KEYWORD        = KtKeywordToken.softKeyword("by", BY_KEYWORD_Type);
    KtKeywordToken GET_KEYWORD       = KtKeywordToken.softKeyword("get", GET_KEYWORD_Type);
    KtKeywordToken SET_KEYWORD       = KtKeywordToken.softKeyword("set", SET_KEYWORD_Type);
    KtKeywordToken CONSTRUCTOR_KEYWORD = KtKeywordToken.softKeyword("constructor", CONSTRUCTOR_KEYWORD_Type);
    KtKeywordToken INIT_KEYWORD        = KtKeywordToken.softKeyword("init", INIT_KEYWORD_Type);
    KtKeywordToken CONTEXT_KEYWORD     = KtKeywordToken.softKeyword("context", CONTEXT_KEYWORD_Type);

    KtModifierKeywordToken ABSTRACT_KEYWORD  = KtModifierKeywordToken.softKeywordModifier("abstract", ABSTRACT_KEYWORD_Type);
    KtModifierKeywordToken ENUM_KEYWORD      = KtModifierKeywordToken.softKeywordModifier("enum", ENUM_KEYWORD_Type);
    KtModifierKeywordToken CONTRACT_KEYWORD  = KtModifierKeywordToken.softKeywordModifier("contract", CONTRACT_KEYWORD_Type);
    KtModifierKeywordToken OPEN_KEYWORD      = KtModifierKeywordToken.softKeywordModifier("open", OPEN_KEYWORD_Type);
    KtModifierKeywordToken INNER_KEYWORD     = KtModifierKeywordToken.softKeywordModifier("inner", INNER_KEYWORD_Type);
    KtModifierKeywordToken OVERRIDE_KEYWORD  = KtModifierKeywordToken.softKeywordModifier("override", OVERRIDE_KEYWORD_Type);
    KtModifierKeywordToken PRIVATE_KEYWORD   = KtModifierKeywordToken.softKeywordModifier("private", PRIVATE_KEYWORD_Type);
    KtModifierKeywordToken PUBLIC_KEYWORD    = KtModifierKeywordToken.softKeywordModifier("public", PUBLIC_KEYWORD_Type);
    KtModifierKeywordToken INTERNAL_KEYWORD  = KtModifierKeywordToken.softKeywordModifier("internal", INTERNAL_KEYWORD_Type);
    KtModifierKeywordToken PROTECTED_KEYWORD = KtModifierKeywordToken.softKeywordModifier("protected", PROTECTED_KEYWORD_Type);
    KtKeywordToken CATCH_KEYWORD     = KtKeywordToken.softKeyword("catch", CATCH_KEYWORD_Type);
    KtModifierKeywordToken OUT_KEYWORD       = KtModifierKeywordToken.softKeywordModifier("out", OUT_KEYWORD_Type);
    KtModifierKeywordToken VARARG_KEYWORD    = KtModifierKeywordToken.softKeywordModifier("vararg", VARARG_KEYWORD_Type);
    KtModifierKeywordToken REIFIED_KEYWORD   = KtModifierKeywordToken.softKeywordModifier("reified", REIFIED_KEYWORD_Type);
    KtKeywordToken DYNAMIC_KEYWORD   = KtKeywordToken.softKeyword("dynamic", DYNAMIC_KEYWORD_Type);
    KtModifierKeywordToken COMPANION_KEYWORD = KtModifierKeywordToken.softKeywordModifier("companion", COMPANION_KEYWORD_Type);
    KtModifierKeywordToken SEALED_KEYWORD    = KtModifierKeywordToken.softKeywordModifier("sealed", SEALED_KEYWORD_Type);

    KtModifierKeywordToken DEFAULT_VISIBILITY_KEYWORD = PUBLIC_KEYWORD;

    KtKeywordToken FINALLY_KEYWORD   = KtKeywordToken.softKeyword("finally", FINALLY_KEYWORD_Type);
    KtModifierKeywordToken FINAL_KEYWORD     = KtModifierKeywordToken.softKeywordModifier("final", FINAL_KEYWORD_Type);

    KtModifierKeywordToken LATEINIT_KEYWORD = KtModifierKeywordToken.softKeywordModifier("lateinit", LATEINIT_KEYWORD_Type);

    KtModifierKeywordToken DATA_KEYWORD    = KtModifierKeywordToken.softKeywordModifier("data", DATA_KEYWORD_Type);
    KtModifierKeywordToken VALUE_KEYWORD    = KtModifierKeywordToken.softKeywordModifier("value", VALUE_KEYWORD_Type);
    KtModifierKeywordToken INLINE_KEYWORD    = KtModifierKeywordToken.softKeywordModifier("inline", INLINE_KEYWORD_Type);
    KtModifierKeywordToken NOINLINE_KEYWORD    = KtModifierKeywordToken.softKeywordModifier("noinline", NOINLINE_KEYWORD_Type);
    KtModifierKeywordToken TAILREC_KEYWORD    = KtModifierKeywordToken.softKeywordModifier("tailrec", TAILREC_KEYWORD_Type);
    KtModifierKeywordToken EXTERNAL_KEYWORD    = KtModifierKeywordToken.softKeywordModifier("external", EXTERNAL_KEYWORD_Type);
    KtModifierKeywordToken ANNOTATION_KEYWORD    = KtModifierKeywordToken.softKeywordModifier("annotation", ANNOTATION_KEYWORD_Type);
    KtModifierKeywordToken CROSSINLINE_KEYWORD    = KtModifierKeywordToken.softKeywordModifier("crossinline", CROSSINLINE_KEYWORD_Type);
    KtModifierKeywordToken OPERATOR_KEYWORD = KtModifierKeywordToken.softKeywordModifier("operator", OPERATOR_KEYWORD_Type);
    KtModifierKeywordToken INFIX_KEYWORD = KtModifierKeywordToken.softKeywordModifier("infix", INFIX_KEYWORD_Type);

    KtModifierKeywordToken CONST_KEYWORD = KtModifierKeywordToken.softKeywordModifier("const", CONST_KEYWORD_Type);

    KtModifierKeywordToken SUSPEND_KEYWORD = KtModifierKeywordToken.softKeywordModifier("suspend", SUSPEND_KEYWORD_Type);

    KtModifierKeywordToken HEADER_KEYWORD = KtModifierKeywordToken.softKeywordModifier("header", HEADER_KEYWORD_Type);
    KtModifierKeywordToken IMPL_KEYWORD = KtModifierKeywordToken.softKeywordModifier("impl", IMPL_KEYWORD_Type);

    KtModifierKeywordToken EXPECT_KEYWORD = KtModifierKeywordToken.softKeywordModifier("expect", EXPECT_KEYWORD_Type);
    KtModifierKeywordToken ACTUAL_KEYWORD = KtModifierKeywordToken.softKeywordModifier("actual", ACTUAL_KEYWORD_Type);


    TokenSet KEYWORDS = TokenSet.create(PACKAGE_KEYWORD, AS_KEYWORD, TYPE_ALIAS_KEYWORD, CLASS_KEYWORD, INTERFACE_KEYWORD,
                                        THIS_KEYWORD, SUPER_KEYWORD, VAL_KEYWORD, VAR_KEYWORD, FUN_KEYWORD, FOR_KEYWORD,
                                        NULL_KEYWORD,
                                        TRUE_KEYWORD, FALSE_KEYWORD, IS_KEYWORD,
                                        IN_KEYWORD, THROW_KEYWORD, RETURN_KEYWORD, BREAK_KEYWORD, CONTINUE_KEYWORD, OBJECT_KEYWORD, IF_KEYWORD,
                                        ELSE_KEYWORD, WHILE_KEYWORD, DO_KEYWORD, TRY_KEYWORD, WHEN_KEYWORD,
                                        NOT_IN, NOT_IS, AS_SAFE,
                                        TYPEOF_KEYWORD
    );

    TokenSet SOFT_KEYWORDS = TokenSet.create(FILE_KEYWORD, IMPORT_KEYWORD, WHERE_KEYWORD, BY_KEYWORD, GET_KEYWORD,
                                             SET_KEYWORD, ABSTRACT_KEYWORD, ENUM_KEYWORD, CONTRACT_KEYWORD, OPEN_KEYWORD, INNER_KEYWORD,
                                             OVERRIDE_KEYWORD, PRIVATE_KEYWORD, PUBLIC_KEYWORD, INTERNAL_KEYWORD, PROTECTED_KEYWORD,
                                             CATCH_KEYWORD, FINALLY_KEYWORD, OUT_KEYWORD, FINAL_KEYWORD, VARARG_KEYWORD, REIFIED_KEYWORD,
                                             DYNAMIC_KEYWORD, COMPANION_KEYWORD, CONSTRUCTOR_KEYWORD, INIT_KEYWORD, SEALED_KEYWORD,
                                             FIELD_KEYWORD, PROPERTY_KEYWORD, RECEIVER_KEYWORD, PARAM_KEYWORD, SETPARAM_KEYWORD,
                                             DELEGATE_KEYWORD,
                                             LATEINIT_KEYWORD,
                                             DATA_KEYWORD, INLINE_KEYWORD, NOINLINE_KEYWORD, TAILREC_KEYWORD, EXTERNAL_KEYWORD,
                                             ANNOTATION_KEYWORD, CROSSINLINE_KEYWORD, CONST_KEYWORD, OPERATOR_KEYWORD, INFIX_KEYWORD,
                                             SUSPEND_KEYWORD, HEADER_KEYWORD, IMPL_KEYWORD, EXPECT_KEYWORD, ACTUAL_KEYWORD,
                                             VALUE_KEYWORD, CONTEXT_KEYWORD
    );

    /*
        This array is used in stub serialization:
        1. Do not change order.
        2. If you add an entry or change order, increase stub version.
     */
    KtModifierKeywordToken[] MODIFIER_KEYWORDS_ARRAY =
            new KtModifierKeywordToken[] {
                    ABSTRACT_KEYWORD, ENUM_KEYWORD, CONTRACT_KEYWORD, OPEN_KEYWORD, INNER_KEYWORD, OVERRIDE_KEYWORD, PRIVATE_KEYWORD,
                    PUBLIC_KEYWORD, INTERNAL_KEYWORD, PROTECTED_KEYWORD, OUT_KEYWORD, IN_KEYWORD, FINAL_KEYWORD, VARARG_KEYWORD,
                    REIFIED_KEYWORD, COMPANION_KEYWORD, SEALED_KEYWORD, LATEINIT_KEYWORD,
                    DATA_KEYWORD, INLINE_KEYWORD, NOINLINE_KEYWORD, TAILREC_KEYWORD, EXTERNAL_KEYWORD, ANNOTATION_KEYWORD, CROSSINLINE_KEYWORD,
                    CONST_KEYWORD, OPERATOR_KEYWORD, INFIX_KEYWORD, SUSPEND_KEYWORD,
                    HEADER_KEYWORD, IMPL_KEYWORD, EXPECT_KEYWORD, ACTUAL_KEYWORD, FUN_KEYWORD, VALUE_KEYWORD
            };

    TokenSet MODIFIER_KEYWORDS = TokenSet.create(MODIFIER_KEYWORDS_ARRAY);

    TokenSet TYPE_MODIFIER_KEYWORDS = TokenSet.create(SUSPEND_KEYWORD);
    TokenSet TYPE_ARGUMENT_MODIFIER_KEYWORDS = TokenSet.create(IN_KEYWORD, OUT_KEYWORD);
    TokenSet RESERVED_VALUE_PARAMETER_MODIFIER_KEYWORDS = TokenSet.create(OUT_KEYWORD, VARARG_KEYWORD);

    TokenSet VISIBILITY_MODIFIERS = TokenSet.create(PRIVATE_KEYWORD, PUBLIC_KEYWORD, INTERNAL_KEYWORD, PROTECTED_KEYWORD);
    TokenSet MODALITY_MODIFIERS = TokenSet.create(ABSTRACT_KEYWORD, FINAL_KEYWORD, SEALED_KEYWORD, OPEN_KEYWORD);

    TokenSet WHITESPACES = TokenSet.create(TokenType.WHITE_SPACE);

    /**
     * Don't add KDocTokens to COMMENTS TokenSet, because it is used in KotlinParserDefinition.getCommentTokens(),
     * and therefor all COMMENTS tokens will be ignored by PsiBuilder.
     *
     * @see KtPsiUtil#isInComment(com.intellij.psi.PsiElement)
     */
    TokenSet COMMENTS = TokenSet.create(EOL_COMMENT, BLOCK_COMMENT, DOC_COMMENT, SHEBANG_COMMENT);
    TokenSet WHITE_SPACE_OR_COMMENT_BIT_SET = TokenSet.orSet(COMMENTS, WHITESPACES);

    TokenSet STRINGS = TokenSet.create(CHARACTER_LITERAL, REGULAR_STRING_PART);
    TokenSet OPERATIONS = TokenSet.create(AS_KEYWORD, AS_SAFE, IS_KEYWORD, IN_KEYWORD, DOT, PLUSPLUS, MINUSMINUS, EXCLEXCL, MUL, PLUS,
                                          MINUS, EXCL, DIV, PERC, LT, GT, LTEQ, GTEQ, EQEQEQ, EXCLEQEQEQ, EQEQ, EXCLEQ, ANDAND, OROR,
                                          SAFE_ACCESS, ELVIS,
                                          RANGE, RANGE_UNTIL, EQ, MULTEQ, DIVEQ, PERCEQ, PLUSEQ, MINUSEQ,
                                          NOT_IN, NOT_IS,
                                          IDENTIFIER);

    TokenSet AUGMENTED_ASSIGNMENTS = TokenSet.create(PLUSEQ, MINUSEQ, MULTEQ, PERCEQ, DIVEQ);
    TokenSet ALL_ASSIGNMENTS = TokenSet.create(EQ, PLUSEQ, MINUSEQ, MULTEQ, PERCEQ, DIVEQ);
    TokenSet INCREMENT_AND_DECREMENT = TokenSet.create(PLUSPLUS, MINUSMINUS);
    TokenSet QUALIFIED_ACCESS = TokenSet.create(DOT_QUALIFIED_EXPRESSION, SAFE_ACCESS_EXPRESSION);
}
