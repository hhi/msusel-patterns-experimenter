/**
 * The MIT License (MIT)
 * 
 * Sonar Technical Asset Plugin
 * Copyright (c) 2015 Isaac Griffith, SiliconCode, LLC
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
package edu.montana.gsoc.msusel.patterns.td;

import com.sparqline.techdebt.param.TDParams;

/**
 * ChinTD -
 * 
 * @author Isaac Griffith
 */
public class ChinTD extends TechnicalDebtCalcStrategy {

    /**
     * 
     */
    private static final String YEARS_OF_MAINTENANCE       = "yearsOfMaintenance";
    /**
     * 
     */
    private static final String REMAINING_YEARS_ACTIVE_DEV = "remainingYearsActiveDev";
    /**
     * 
     */
    private static final String COMPOUNDING_INTEREST       = "compoundingInterest";
    /**
     * 
     */
    private static final String RECURRING_INTEREST         = "recurringInterest";

    /**
     * {@inheritDoc}
     */
    @Override
    public double calculate(TDParams param)
    {
        double recurringInterest = param.getNumericParam(RECURRING_INTEREST);
        double compoundingInterest = param.getNumericParam(COMPOUNDING_INTEREST);

        double remainingYearsActiveDev = param.getNumericParam(REMAINING_YEARS_ACTIVE_DEV);
        double yearsOfMaintenance = param.getNumericParam(YEARS_OF_MAINTENANCE);

        double activeTD = 0.0;
        for (int currentYear = 0; currentYear < remainingYearsActiveDev - 1; currentYear++)
        {
            activeTD += recurringInterest * Math.pow((1 + compoundingInterest), currentYear);
        }

        double maintenanceTD = recurringInterest * Math.pow((1 + compoundingInterest), remainingYearsActiveDev)
                * yearsOfMaintenance;

        double principal = activeTD + maintenanceTD;

        return principal;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TDParams generateParams()
    {
        // TODO Auto-generated method stub
        return null;
    }
}