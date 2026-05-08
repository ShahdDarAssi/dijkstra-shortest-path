# Multi-Criteria Shortest Path Optimization
Using Graphs and Dijkstra's Algorithm with Priority Queue

## Project Overview

This project implements a **graph-based routing system** that solves the shortest path problem using **Dijkstra's Algorithm with a Priority Queue**.

Unlike traditional shortest path systems that rely on a single metric, this system supports **multi-criteria optimization**, allowing users to choose between:

-  Shortest Distance
-  Minimum Travel Time
-  Both — compare results for distance and time

Each road in the graph contains two weights:
- **Distance** (km)
- **Travel Time** (minutes)

This makes the system closer to real-world navigation systems like GPS applications.

---

## Abstract

Finding optimal routes in urban road networks is a fundamental problem in transportation systems and emergency services. In real-world scenarios, the optimal route is not always defined by the shortest distance; often, the least travel time is more important due to traffic conditions or road characteristics.

This project presents a graph-based routing system that applies **Dijkstra's algorithm with a Priority Queue** to compute optimal paths based on two criteria: shortest distance or minimum travel time.

The system is **efficient**, **scalable**, and suitable for real-world navigation systems.

---

## Features

| Feature | Description |
|---------|-------------|
| Dijkstra's Algorithm | Guarantees optimal path for non-negative weights |
| Priority Queue | Efficiently selects the next lowest-cost node |
| Multi-Criteria | Optimize by Distance, Time, or Both |
| Adjacency List | Efficient graph representation |
| Scalable | Handles large road networks |

---

## Input Format

The input is provided as a `.txt` file:

```
Node1  Node2  Distance  Time
```

**Example:**
```
A D 3.0  5
A B 4.5  7
A C 2.0  4
B C 1.2  3
C D 6.0 10
```

---

## Optimization Modes

###  Distance Mode
Finds the shortest path based on **total distance (km)**

###  Time Mode
Finds the fastest path based on **total travel time (minutes)**

###  Both Mode
Displays **both** optimized results side by side for comparison

---

## Algorithm

The system uses **Dijkstra's Algorithm**:

```
1. Start at source node with cost = 0
2. Push source into Priority Queue
3. While queue is not empty:
   a. Pop node with lowest cost
   b. If destination reached → stop
   c. For each neighbor → relax edge if better path found
4. Reconstruct optimal path using prev[] array
```

 Weight used = **Distance** or **Time** depending on selected mode

---

## Sample Output

```
==================================================
Optimization by: Distance
Optimal Path: A -> C -> B -> D
Total Distance: 8.20 km
==================================================

==================================================
Optimization by: Travel Time
Optimal Path: A -> C -> D
Total Travel Time: 14.00 minutes
==================================================
```

---

## Conclusion

This project demonstrates an **efficient and scalable** routing system using graph theory and Dijkstra's algorithm. By supporting multiple optimization criteria, it reflects real-world navigation requirements more accurately than traditional single-metric systems.

The use of a **Priority Queue** ensures optimal performance even on large-scale graphs.

---

## Author
**Shahd Dar Assi**  
