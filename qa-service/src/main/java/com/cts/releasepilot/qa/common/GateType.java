package com.cts.releasepilot.qa.common;

/** Type of quality gate evaluated for a release. */
public enum GateType {
    TestPassRate,
    SecurityScan,
    PerformanceBenchmark,
    ManualApproval
}
