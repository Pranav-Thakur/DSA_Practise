#!/bin/bash

# DSA Practice Project Build Script

echo "Building DSA Practice Project..."

# Create output directory if it doesn't exist
mkdir -p out

# Compile all Java files
echo "Compiling Java files..."
find src -name "*.java" -exec javac -d out {} \;

if [ $? -eq 0 ]; then
    echo "Compilation successful!"
    echo ""
    echo "Available test classes:"
    echo "1. TrieTest - Run with: java -cp out trie.TrieTest"
    echo "2. DPTestSuite - Run with: java -cp out dp.DPTestSuite"
    echo ""
    echo "Individual solutions can be run from their respective packages."
else
    echo "Compilation failed!"
    exit 1
fi
