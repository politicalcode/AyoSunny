/*
 * Copyright JiongBull 2016
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.ayo.jlog.printer;


import org.ayo.jlog.constant.JLogLevel;
import org.ayo.jlog.util.JSysUtils;

/**
 * 打印接口.
 */
public interface JPrinter {

    String LINE_SEPARATOR = JSysUtils.getLineSeparator();

    /**
     * 日志打印输出到控制台.
     *
     * @param level   级别
     * @param tag     标签
     * @param message 信息
     * @param element 堆栈元素
     */
    void printConsole(JLogLevel level, String tag, String message, StackTraceElement element);

    /**
     * 日志打印输出到文件.
     *
     * @param level   级别
     * @param tag     标签
     * @param message 信息
     * @param element 堆栈元素
     */
    void printFile(JLogLevel level, String tag, String message, StackTraceElement element);
}
