/*
 * Copyright 2015 Google Inc. All Rights Reserved.
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

package com.google.doubleclick.util;

import com.google.common.base.MoreObjects;

/**
 * Key for the DMA Region mapping.  This composite key is necessary
 * because cities can belong to multiple DMA Regions.
 */
@Deprecated
public class CityDMARegionKey {
  private final int criteriaId;
  private final String regionName;

  public CityDMARegionKey(int criteriaId, String regionName) {
    this.criteriaId = criteriaId;
    this.regionName = regionName;
  }

  public final int criteriaId() {
    return criteriaId;
  }

  public final String regionName() {
    return regionName;
  }

  @Override public int hashCode() {
    return criteriaId ^ regionName.hashCode();
  }

  @Override public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    } else if (!(obj instanceof CityDMARegionKey)) {
      return false;
    }

    CityDMARegionKey other = (CityDMARegionKey) obj;
    return criteriaId == other.criteriaId && regionName.equals(other.regionName);
  }

  @Override public String toString() {
    return MoreObjects.toStringHelper(this).omitNullValues()
        .add("criteriaId", criteriaId)
        .add("regionName", regionName)
        .toString();
  }
}