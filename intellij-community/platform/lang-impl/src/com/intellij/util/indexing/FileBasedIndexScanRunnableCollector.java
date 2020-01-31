// Copyright 2000-2019 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
package com.intellij.util.indexing;

import com.intellij.ide.lightEdit.LightEditUtil;
import com.intellij.openapi.fileTypes.FileTypeManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.roots.ProjectFileIndex;
import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.NotNull;

/**
 * @deprecated Use {@link FileBasedIndexRootsCollector} instead.
 */
@Deprecated
public final class FileBasedIndexScanRunnableCollector {

  private final Project myProject;
  private final @NotNull ProjectFileIndex myProjectFileIndex;

  public FileBasedIndexScanRunnableCollector(Project project) {
    myProject = project;
    myProjectFileIndex = ProjectFileIndex.getInstance(project);
  }

  public static FileBasedIndexScanRunnableCollector getInstance(@NotNull Project project) {
    return new FileBasedIndexScanRunnableCollector(project);
  }

  /**
   * @deprecated Use ProjectFileIndex directly.
   */
  @Deprecated
  public final boolean shouldCollect(@NotNull final VirtualFile file) {
    if (LightEditUtil.isLightEditProject(myProject)) {
      return false;
    }
    if (myProjectFileIndex.isInContent(file) || myProjectFileIndex.isInLibrary(file)) {
      return !FileTypeManager.getInstance().isFileIgnored(file);
    }
    return false;
  }
}
