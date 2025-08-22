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
    echo "1. TestRunner - Run all tests: java -cp out TestRunner"
    echo "2. TrieTest - Run with: java -cp out trie.TrieTest"
    echo "3. DPTestSuite - Run with: java -cp out dp.DPTestSuite"
    echo "4. GraphTest - Run with: java -cp out graph.GraphTest"
    echo "5. PalindromePartitionTest - Run with: java -cp out dp.PalindromePartitionTest"
    echo ""
    echo "Individual solutions can be run from their respective packages."
    echo ""
    echo "Examples:"
    echo "  java -cp out TestRunner          # Run all tests"
    echo "  java -cp out TestRunner trie     # Run only Trie tests"
    echo "  java -cp out TestRunner dp       # Run only DP tests"
    echo "  java -cp out TestRunner graph    # Run only Graph tests"
else
    echo "Compilation failed!"
    exit 1
fi
