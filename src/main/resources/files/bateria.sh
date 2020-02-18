#!/bin/bash

BATLVL=$(cat /sys/class/power_supply/BAT0/capacity)
BAToAC=$(cat /sys/class/power_supply/AC/online)

# AC not connected
if [ $BAToAC -eq 0 ]; then
    # Disable Intel Turbo Boost
    echo 1 > /sys/devices/system/cpu/intel_pstate/no_turbo
    if [ $BATLVL -le 23 ]; then
        # Low battery --> Very limited frecuency CPU
        echo 64 > /sys/devices/system/cpu/intel_pstate/max_perf_pct
    elif [ $BATLVL -le 50 ]; then
        # Normal battery level --> Limited frecuency CPU
        echo 73 > /sys/devices/system/cpu/intel_pstate/max_perf_pct
    elif [ $BATLVL -le 80 ]; then
        # High battery --> ~Limited frecuency CPU
        echo 85 > /sys/devices/system/cpu/intel_pstate/max_perf_pct
    else
        # Very high battery --> Normal frecuency CPU
        echo 99 > /sys/devices/system/cpu/intel_pstate/max_perf_pct
    fi
# AC connected
else
    # Enable Intel Turbo Boost and full CPU power
    echo 100 > /sys/devices/system/cpu/intel_pstate/max_perf_pct
    echo 0 > /sys/devices/system/cpu/intel_pstate/no_turbo
fi
