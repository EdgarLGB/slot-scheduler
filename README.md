# Slot scheduler

## Problem
In the multi-slot banner, each slot would be filled with an ad from a predefined feed. Each feed might contain tens of ads.
We want to show the ads in a way that the user will see the ads as diversified as possible.

One banner contains multiple slots, and each slot is associated with a feed. A feed contains a list of ads. And we have the same amount of slots and feeds.

## Solution
We use [OptaPlanner](https://www.optaplanner.org/docs/optaplanner/latest/quickstart/hello-world/hello-world-quickstart.html) library to solve this CSP problem.
We applied three constraints to solve the problem:

1. Hard constraint: slotShouldBeAssignedWithItemOfCorrespondingFeedId
2. Hard constraint: noSameItemAppearingInAdjacentGroups
3. Soft constraint: discourageItemReusePerSlotIdAcrossAllGroups

By default, OptaPlanner is using LateAcceptance (similar to Simulated Annealing, but without time-based pruning) to find the optima.
But you can try different ones like Tabu Search, Hill Climbing, SA, etc.

## How to run
Run the main function to show the demo which is using some dummy data.

Please feel free to modify it with your own data.
