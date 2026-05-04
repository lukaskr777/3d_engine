# Swing3D

Swing3D is a small Java/Swing 3D rendering engine built from scratch. It projects 3D points onto a 2D Swing canvas, draws cuboids from rectangle faces, supports rotation and zooming, and includes object-group rendering that sorts visible faces for correct overlap.

## Why It Is Interesting

- Implements perspective projection without a graphics engine.
- Models 3D primitives as points, rectangles, cuboids, and grouped objects.
- Supports keyboard-driven rotation around the X, Y, and Z axes.
- Includes face filtering and depth sorting for rendering many cuboids efficiently.

## Run

From the parent directory of this folder:

```powershell
javac Swing3D\*.java
java Swing3D.RunG
```

Controls in the demo:

- Arrow keys: rotate around X/Y
- `R`: rotate around Z
- `Z` / `X`: zoom in/out

## YC Answer Draft

I built Swing3D, a Java/Swing 3D rendering engine from scratch that implements perspective projection, 3D primitives, rotation, zooming, face filtering, and depth sorting without using a game engine or graphics library.
