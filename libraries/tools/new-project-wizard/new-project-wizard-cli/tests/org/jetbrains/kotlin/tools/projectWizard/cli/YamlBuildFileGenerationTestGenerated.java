/*
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.tools.projectWizard.cli;

import com.intellij.testFramework.TestDataPath;
import org.jetbrains.kotlin.test.JUnit3RunnerWithInners;
import org.jetbrains.kotlin.test.KotlinTestUtils;
import org.jetbrains.kotlin.test.TestMetadata;
import org.junit.runner.RunWith;

import java.io.File;
import java.util.regex.Pattern;

/** This class is generated by {@link org.jetbrains.kotlin.generators.tests.TestsPackage}. DO NOT MODIFY MANUALLY */
@SuppressWarnings("all")
@TestMetadata("libraries/tools/new-project-wizard/new-project-wizard-cli/testData/buildFileGeneration")
@TestDataPath("$PROJECT_ROOT")
@RunWith(JUnit3RunnerWithInners.class)
public class YamlBuildFileGenerationTestGenerated extends AbstractYamlBuildFileGenerationTest {
    private void runTest(String testDataFilePath) throws Exception {
        KotlinTestUtils.runTest(this::doTest, this, testDataFilePath);
    }

    public void testAllFilesPresentInBuildFileGeneration() throws Exception {
        KotlinTestUtils.assertAllTestsPresentByMetadataWithExcluded(this.getClass(), new File("libraries/tools/new-project-wizard/new-project-wizard-cli/testData/buildFileGeneration"), Pattern.compile("^([^\\.]+)$"), null, false);
    }

    @TestMetadata("android")
    public void testAndroid() throws Exception {
        runTest("libraries/tools/new-project-wizard/new-project-wizard-cli/testData/buildFileGeneration/android/");
    }

    @TestMetadata("jsNodeAndBrowserTargets")
    public void testJsNodeAndBrowserTargets() throws Exception {
        runTest("libraries/tools/new-project-wizard/new-project-wizard-cli/testData/buildFileGeneration/jsNodeAndBrowserTargets/");
    }

    @TestMetadata("jvmTarget")
    public void testJvmTarget() throws Exception {
        runTest("libraries/tools/new-project-wizard/new-project-wizard-cli/testData/buildFileGeneration/jvmTarget/");
    }

    @TestMetadata("jvmTargetWithJava")
    public void testJvmTargetWithJava() throws Exception {
        runTest("libraries/tools/new-project-wizard/new-project-wizard-cli/testData/buildFileGeneration/jvmTargetWithJava/");
    }

    @TestMetadata("jvmToJvmDependency")
    public void testJvmToJvmDependency() throws Exception {
        runTest("libraries/tools/new-project-wizard/new-project-wizard-cli/testData/buildFileGeneration/jvmToJvmDependency/");
    }

    @TestMetadata("jvmToJvmDependencyWithSingleRoot")
    public void testJvmToJvmDependencyWithSingleRoot() throws Exception {
        runTest("libraries/tools/new-project-wizard/new-project-wizard-cli/testData/buildFileGeneration/jvmToJvmDependencyWithSingleRoot/");
    }

    @TestMetadata("kotlinJvm")
    public void testKotlinJvm() throws Exception {
        runTest("libraries/tools/new-project-wizard/new-project-wizard-cli/testData/buildFileGeneration/kotlinJvm/");
    }

    @TestMetadata("nativeForCurrentSystem")
    public void testNativeForCurrentSystem() throws Exception {
        runTest("libraries/tools/new-project-wizard/new-project-wizard-cli/testData/buildFileGeneration/nativeForCurrentSystem/");
    }

    @TestMetadata("simpleMultiplatform")
    public void testSimpleMultiplatform() throws Exception {
        runTest("libraries/tools/new-project-wizard/new-project-wizard-cli/testData/buildFileGeneration/simpleMultiplatform/");
    }

    @TestMetadata("simpleNativeTarget")
    public void testSimpleNativeTarget() throws Exception {
        runTest("libraries/tools/new-project-wizard/new-project-wizard-cli/testData/buildFileGeneration/simpleNativeTarget/");
    }

    @TestMetadata("singlePlatformJsBrowser")
    public void testSinglePlatformJsBrowser() throws Exception {
        runTest("libraries/tools/new-project-wizard/new-project-wizard-cli/testData/buildFileGeneration/singlePlatformJsBrowser/");
    }

    @TestMetadata("singlePlatformJsNode")
    public void testSinglePlatformJsNode() throws Exception {
        runTest("libraries/tools/new-project-wizard/new-project-wizard-cli/testData/buildFileGeneration/singlePlatformJsNode/");
    }
}
