#!/bin/bash

# Installation script for Zip-Mod
# This script will download and use Gradle 8.6 to build the mod

set -e

echo "========================================="
echo "Zip-Mod Build Script"
echo "========================================="

# Check if we're in the right directory
if [ ! -f "build.gradle" ]; then
    echo "Error: build.gradle not found!"
    echo "Please run this script from the Zip-Mod directory"
    exit 1
fi

echo "✓ Found build.gradle"

# Create temporary directory for gradle
GRADLE_HOME="/tmp/gradle-8.6"

if [ ! -d "$GRADLE_HOME" ]; then
    echo "Downloading Gradle 8.6..."
    cd /tmp
    wget -q https://services.gradle.org/distributions/gradle-8.6-bin.zip -O gradle-8.6.zip
    unzip -q gradle-8.6.zip
    rm gradle-8.6.zip
    echo "✓ Gradle 8.6 downloaded"
    cd -
else
    echo "✓ Gradle 8.6 already exists"
fi

# Set gradle path
export PATH="$GRADLE_HOME/bin:$PATH"

echo ""
echo "Starting build..."
echo "This may take 5-10 minutes on first run..."
echo ""

# Build
gradle build

echo ""
echo "========================================="
echo "✓ Build successful!"
echo "JAR file: build/libs/zipmod-1.0.0.jar"
echo "========================================="
