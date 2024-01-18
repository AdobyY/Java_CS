package com.shpp.p2p.cs.byarinko.assignment6.hg.teest;

public class FailureResult extends TeestResult {
    public static final FailureResult INSTANCE = new FailureResult();

    public ResultTypeHolder.ResultType getType() {
        return ResultTypeHolder.ResultType.FAILURE;
    }

    public String toString() {
        return "This test failed.  This means that there is a bug somewhere in your code.";
    }
}
