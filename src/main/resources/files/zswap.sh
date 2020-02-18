#!/bin/bash

if [ $(id -u) -ne 0 ]; then
    echo "Please run as root"
    exit 1
fi

if test -e /sys/module/zswap; then
    echo "Enabling lz4 for compressor"
    echo lz4 > /sys/module/zswap/parameters/compressor
    echo "The current max_pool_percent is setting to 25"
    echo 25 > /sys/module/zswap/parameters/max_pool_percent
    echo "Using z3fold for zpool"
    echo z3fold > /sys/module/zswap/parameters/zpool
    if test -e /sys/module/zswap/parameters/same_filled_pages_enabled; then
        echo "Enabling same_filled_pages"
        echo 1 > /sys/module/zswap/parameters/same_filled_pages_enabled
    fi
    echo 1 > /sys/module/zswap/parameters/enabled
    echo "zswap enabled!"
    echo "Execute as root 'grep -R . /sys/kernel/debug/zswap' to check zswap"
else
    echo "Your kernel doesn't support zswap"
fi
