package com.cts.releasepilot.release.common;

/** Lifecycle status of a release package. */
public enum ReleaseStatus {
    Draft,
    PackagingComplete,
    QATesting,
    SignedOff,
    Released,
    Recalled
}
