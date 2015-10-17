package com.myapp.calculator;

import junit.framework.Assert;

import org.junit.Test;

import java.math.BigDecimal;

/**
 * Android calculator app
 */
public class FibonacciTests {

    @Test
    public void testFibonacciCorrectness(){
        for (int i=0; i<=100; ++i){
            BigDecimal n1 = Fibonacci.fibonacciDynamicProgramming(i);
            BigDecimal n2 = Fibonacci.fibonacciEfficientRecursion(i);
            Assert.assertEquals(n1, n2);
            // System.out.println(n1);
        }
        // TODO: add randomized tests.
    }

    // TODO: Add performance tests.
    @Test
    public void testFibonacciPerformance(){
        Assert.assertTrue(true);
    }

    @Test
    public void testDoubleUpdate(){
        for (int i=0; i<=100; ++i){
            BigDecimal n1 = Fibonacci.fibonacciDynamicProgramming(i);
            BigDecimal n2 = Fibonacci.fibonacciDynamicProgramming(i+1);
            Pair<BigDecimal,BigDecimal> pair = Fibonacci.doubleUpdate(n1,n2);
            Assert.assertEquals(pair.getFirst(), Fibonacci.fibonacciDynamicProgramming(2*i));
            Assert.assertEquals(pair.getSecond(), Fibonacci.fibonacciDynamicProgramming(2*i+1));
        }
    }
}
