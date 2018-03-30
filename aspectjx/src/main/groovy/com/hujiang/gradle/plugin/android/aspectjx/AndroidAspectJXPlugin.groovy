/*
 * Copyright 2016 firefly1126, Inc.

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
 * limitations under the License.gradle_plugin_android_aspectjx
 */
// This plugin is based on https://github.com/JakeWharton/hugo
package com.hujiang.gradle.plugin.android.aspectjx

import com.android.build.gradle.AppPlugin
import com.android.build.gradle.AppExtension
import org.gradle.api.Plugin
import org.gradle.api.Project

/**
 * aspectj plugin,
 * @author simon
 * @version 1.0.0
 * @since 2016-04-20
 */
class AndroidAspectJXPlugin implements Plugin<Project> {

    @Override
    void apply(Project project) {

        project.repositories {
            mavenLocal()
        }

        project.dependencies {
            compile 'org.aspectj:aspectjrt:1.8.9'
        }

        project.extensions.create("aspectjx", AspectjExtension)

        if (project.plugins.hasPlugin(AppPlugin)) {
            //build time trace
            project.gradle.addListener(new TimeTrace())

            //register AspectTransform
            AppExtension android = project.extensions.getByType(AppExtension)
//            android.registerTransform(new AspectTransform(project))
            android.registerTransform(new AspectJXTransform(project))
        }
    }
}
