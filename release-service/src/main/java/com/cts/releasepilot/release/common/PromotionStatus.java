package com.cts.releasepilot.release.common;

/** Lifecycle status of a promotion request. */
public enum PromotionStatus {
    Pending,
    Approved,
    InProgress,
    Completed,
    RolledBack,
    Rejected
}
