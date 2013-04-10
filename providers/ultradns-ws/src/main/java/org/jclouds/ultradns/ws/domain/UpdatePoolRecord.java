/**
 * Licensed to jclouds, Inc. (jclouds) under one or more
 * contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  jclouds licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.jclouds.ultradns.ws.domain;

import static com.google.common.base.Objects.equal;
import static com.google.common.base.Objects.toStringHelper;
import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import com.google.common.base.Objects;

/**
 * holds updates for a record
 * 
 * @author Adrian Cole
 */
public final class UpdatePoolRecord {

   /**
    * @param spec what to prime updates from
    * @param pointsTo new value to point to.
    */
   public static UpdatePoolRecord pointingTo(PoolRecordSpec spec, String pointsTo) {
      return new Builder().from(spec).pointsTo(pointsTo).build();
   }

   private final String pointsTo;
   private final String mode;
   private final int priority;
   private final int weight;
   private final int failOverDelay;
   private final int threshold;
   private final int ttl;

   private UpdatePoolRecord(String pointsTo, String mode, int priority, int weight, int failOverDelay, int threshold,
         int ttl) {
      this.pointsTo = checkNotNull(pointsTo, "pointsTo");
      this.mode = checkNotNull(mode, "mode for %s", pointsTo);
      this.priority = priority;
      this.weight = weight;
      checkArgument(weight >= 0, "weight of %s must be >= 0", pointsTo);
      this.failOverDelay = failOverDelay;
      checkArgument(failOverDelay >= 0, "failOverDelay of %s must be >= 0", pointsTo);
      this.threshold = threshold;
      checkArgument(threshold >= 0, "threshold of %s must be >= 0", pointsTo);
      this.ttl = ttl;
      checkArgument(ttl >= 0, "ttl of %s must be >= 0", pointsTo);
   }

   /**
    * correlates to {@link TrafficControllerPoolRecord#getPointsTo()}
    */
   public String getPointsTo() {
      return pointsTo;
   }

   /**
    * correlates to {@link PoolRecordSpec#getState()}
    */
   public String getMode() {
      return mode;
   }

   /**
    * correlates to {@link PoolRecordSpec#getPriority()}
    */
   public int getPriority() {
      return priority;
   }

   /**
    * correlates to {@link PoolRecordSpec#getWeight()}
    */
   public int getWeight() {
      return weight;
   }

   /**
    * correlates to {@link PoolRecordSpec#getFailOverDelay()}
    */
   public int getFailOverDelay() {
      return failOverDelay;
   }

   /**
    * correlates to {@link PoolRecordSpec#getThreshold()}
    */
   public int getThreshold() {
      return threshold;
   }

   /**
    * correlates to {@link PoolRecordSpec#getTTL()}
    */
   public int getTTL() {
      return ttl;
   }

   @Override
   public int hashCode() {
      return Objects.hashCode(pointsTo, mode, priority, weight, failOverDelay, threshold, ttl);
   }

   @Override
   public boolean equals(Object obj) {
      if (this == obj)
         return true;
      if (obj == null)
         return false;
      if (getClass() != obj.getClass())
         return false;
      UpdatePoolRecord that = UpdatePoolRecord.class.cast(obj);
      return equal(this.pointsTo, that.pointsTo) && equal(this.mode, that.mode) && equal(this.priority, that.priority)
            && equal(this.weight, that.weight) && equal(this.failOverDelay, that.failOverDelay)
            && equal(this.threshold, that.threshold) && equal(this.ttl, that.ttl);
   }

   @Override
   public String toString() {
      return toStringHelper(this).add("pointsTo", pointsTo).add("mode", mode).add("priority", priority)
            .add("weight", weight).add("failOverDelay", failOverDelay).add("threshold", threshold).add("ttl", ttl)
            .toString();
   }

   public static Builder builder() {
      return new Builder();
   }

   public Builder toBuilder() {
      return new Builder().from(this);
   }

   public final static class Builder {
      private String pointsTo;
      private String mode;
      private int priority;
      private int weight;
      private int failOverDelay;
      private int threshold;
      private int ttl;

      /**
       * @see UpdatePoolRecord#getPointsTo()
       */
      public Builder pointsTo(String pointsTo) {
         this.pointsTo = pointsTo;
         return this;
      }

      /**
       * @see UpdatePoolRecord#getMode()
       */
      public Builder mode(String mode) {
         this.mode = mode;
         return this;
      }

      /**
       * @see UpdatePoolRecord#getPriority()
       */
      public Builder priority(int priority) {
         this.priority = priority;
         return this;
      }

      /**
       * @see UpdatePoolRecord#getWeight()
       */
      public Builder weight(int weight) {
         this.weight = weight;
         return this;
      }

      /**
       * @see UpdatePoolRecord#getFailOverDelay()
       */
      public Builder failOverDelay(int failOverDelay) {
         this.failOverDelay = failOverDelay;
         return this;
      }

      /**
       * @see UpdatePoolRecord#getThreshold()
       */
      public Builder threshold(int threshold) {
         this.threshold = threshold;
         return this;
      }

      /**
       * @see UpdatePoolRecord#getTTL()
       */
      public Builder ttl(int ttl) {
         this.ttl = ttl;
         return this;
      }

      public UpdatePoolRecord build() {
         return new UpdatePoolRecord(pointsTo, mode, priority, weight, failOverDelay, threshold, ttl);
      }

      public Builder from(PoolRecordSpec in) {
         return this.mode(in.getState()).weight(in.getWeight()).failOverDelay(in.getFailOverDelay())
               .threshold(in.getThreshold()).ttl(in.getTTL());
      }

      public Builder from(TrafficControllerPoolRecord in) {
         return this.weight(in.getWeight()).pointsTo(in.getPointsTo()).priority(in.getPriority());
      }

      public Builder from(UpdatePoolRecord in) {
         return this.pointsTo(in.pointsTo).mode(in.mode).priority(in.priority).weight(in.weight)
               .failOverDelay(in.failOverDelay).threshold(in.threshold).ttl(in.ttl);
      }
   }
}
