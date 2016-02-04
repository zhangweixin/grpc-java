/*
 * Copyright 2014, Google Inc. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are
 * met:
 *
 *    * Redistributions of source code must retain the above copyright
 * notice, this list of conditions and the following disclaimer.
 *    * Redistributions in binary form must reproduce the above
 * copyright notice, this list of conditions and the following disclaimer
 * in the documentation and/or other materials provided with the
 * distribution.
 *
 *    * Neither the name of Google Inc. nor the names of its
 * contributors may be used to endorse or promote products derived from
 * this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
 * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT
 * OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package io.grpc;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

import io.grpc.Status.Code;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/** Unit tests for {@link Status}. */
@RunWith(JUnit4.class)
public class StatusTest {
  @Test
  public void verifyExceptionMessage() {
    assertEquals("UNKNOWN", Status.UNKNOWN.asRuntimeException().getMessage());
    assertEquals("CANCELLED: This is a test",
        Status.CANCELLED.withDescription("This is a test").asRuntimeException().getMessage());
    assertEquals("UNKNOWN", Status.UNKNOWN.asException().getMessage());
    assertEquals("CANCELLED: This is a test",
        Status.CANCELLED.withDescription("This is a test").asException().getMessage());
  }

  @Test
  public void impossibleCodeValue() {
    assertEquals(Code.UNKNOWN, Status.fromCodeValue(-1).getCode());
  }

  @Test
  public void sameCauseReturnsSelf() {
    assertSame(Status.CANCELLED, Status.CANCELLED.withCause(null));
  }

  @Test
  public void sameDescriptionReturnsSelf() {
    assertSame(Status.CANCELLED, Status.CANCELLED.withDescription(null));
    assertSame(Status.CANCELLED, Status.CANCELLED.augmentDescription(null));
  }

  @Test
  public void useObjectHashCode() {
    assertEquals(Status.CANCELLED.hashCode(), System.identityHashCode(Status.CANCELLED));
  }
}