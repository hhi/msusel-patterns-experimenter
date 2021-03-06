/**
 * The MIT License (MIT)
 *
 * MSUSEL Arc Framework
 * Copyright (c) 2015-2018 Montana State University, Gianforte School of Computing,
 * Software Engineering Laboratory
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package edu.montana.gsoc.msusel.arc.impl.pattern4;

import edu.montana.gsoc.msusel.arc.command.ToolCommand;
import edu.montana.gsoc.msusel.arc.command.CommandUtils;
import lombok.Builder;
import org.apache.commons.exec.CommandLine;

public class Pattern4Command extends ToolCommand {

    @Builder(buildMethodName = "create")
    public Pattern4Command() {
        super("Pattern4");
    }

    @Override
    public boolean isRequirementsMet() {
        return CommandUtils.verifyFileExists(toolHome, "pattern4.jar");
    }

    @Override
    public CommandLine buildCommandLine() {
        return new CommandLine("java")
            .addArgument("-Xms32m")
            .addArgument("-Xmx512m")
            .addArgument("-jar")
            .addArgument(CommandUtils.normalizePathString(toolHome) + "pattern4.jar")
            .addArgument("-target")
            .addArgument(binaryDirectory)
            .addArgument("-output")
            .addArgument(reportFile);
    }

    @Override
    protected int getExpectedExitValue() {
        return 0;
    }

    @Override
    public String getName() {
        return "Pattern4";
    }
}
