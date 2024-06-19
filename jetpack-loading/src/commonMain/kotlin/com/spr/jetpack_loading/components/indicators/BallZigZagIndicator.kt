package com.spr.jetpack_loading.components.indicators

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color


/*
* We considered the movement path as two inverted triangles.
* Moving the points on the sides is done using linear interpolation.
* In this way, we get the coordinates of the beginning and the end
* of each point and then we calculate with a certain formula which
* points we have to pass through to go from the coordinates of the
* beginning to the end. Here, we have only mentioned the generalities,
* but more complete explanations are written in each section
 */

@Composable
fun BallZigZagIndicator(
    color: Color = Color.White,
    diameter: Float = 30f,
    duration: Int = 1000,
    spacing: Float = diameter
) {


    /*
    *For linear interpolation (calculation of points between origin and destination)
    *  we need a number between zero and one.
    *  Because we want to move on the verticals in order,
    *  we change this number between zero and three.
    * As long as it is between zero and one, we move on the first vertical,
    *  when it is between 1 and 2, we move on the second vertical,
    * and when it is between 2 and 3, we move on the third vertical.
    * Here, in a certain animation duration,
    *  we change a parameter called position from zero to three in order to determine
    *  the location of the circle using this number.
     */
    val position by rememberInfiniteTransition().animateFloat(
        initialValue = 0.0f,
        targetValue = 3.0f,
        animationSpec = infiniteRepeatable(
            animation = tween(duration, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        )
    )




    Canvas(
        modifier = Modifier
    ) {

// ----------------------  set values -----------------------------------

        /*
        *Here we have defined the area of the canvas where we want to draw a triangles.
        *  We also determine the length of the sides of the triangles.
        *  Since we considered the triangle to be isosceles, the parameter
        * is the length of the legs and the parameter is the length of the third side.
         */
        val center = Offset(x = size.width / 2, y = size.height / 2)   // Triangle's center point
        val baseLength = 100f // The length of the base of the triangle
        val height = 150f // The height of the triangle


        // -------------------- Upper triangle ------------------
        /*
        *We said that the route is considered as two upside down triangles.
        *  To draw any triangle, we must first determine the vertices of the triangle.
        *  The vertices of the upper triangle are:
         */
        // Bottom Vertex (the apex):
        val xBottom = center.x
        val yBottom = center.y

        // Top Left Vertex (one of the base points):
        val xTopLeft = xBottom - (baseLength / 2)
        val yTopLeft = yBottom - (height / 2)

        // Top Right Vertex (the other base point):
        val xTopRight = xBottom + (baseLength / 2)
        val yTopRight = yBottom - (height / 2)


        /*
        * We named the point that moves on the upper triangle x1.
        *  Here, it is only initialized, and below,
        * depending on the value of the position between zero and three,
        *  this parameter will have a coordinate on one of the sides;
        * As we said, if the position is between 0 and 1,
        *  it will move on the first side (here, the upper side),
        * if it is between 1 and 2, it will move on the second side (right side),
        *  and if it is between 2 and 3, it will move on the third side (left side).
         */
        var x1: Float = 0f
        var y1: Float = 0f


        // -------------------- Lower triangle ------------------
        /*
        *  The vertices of the lower triangle are:
        */
        // Bottom Vertex (the apex):
        val xTop = center.x
        val yTop = center.y + spacing

        // Top Left Vertex (one of the base points):
        val xBottomLeft = xTop - (baseLength / 2)
        val yBottomLeft = yTop + (height / 2)

        // Top Right Vertex (the other base point):
        val xBottomRight = xTop + (baseLength / 2)
        val yBottomRight = yTop + (height / 2)


        /*
        * We named the point that moves on the upper triangle x2.
         */
        var x2: Float = 0f
        var y2: Float = 0f


        // -------------------- Calculate position ------------------

        /*
        * The coordinates of the circle that is supposed to move on the
        *  triangular path (x1,y1 and x2,y2) are calculated based on the
        * position value in this part.
        * If the position is between zero and one, we must move on the upper
        *  side of the upper triangle, and on the lower triangle we will move
        * on the lower side. We use this formula to calculate coordinates: (a + t * (b - a))
        * Where "a" is the origin of movement,
        *  "b" is the destination and "t" is the position value,
        *  which is a value between zero and one
         */

        when (position) {
            in 0.0..1.0 -> {

                /*
                *For example, if the position is between zero and one,
                * we calculate the coordinates of the circle that moves on the upper triangle:
                *  the origin of movement is from the upper vertex, the left side, which is named xTopLeft here,
                *  and the destination is the upper right vertex, which is named xTopRight. is called.
                 */
                x1 = (xTopLeft + position * (xTopRight - xTopLeft))
                y1 = (yTopLeft + position * (yTopRight - yTopLeft))

                /*
                * Calculate the coordinates of the circle that is on the lower triangle
                 */
                x2 = (xBottomRight + position * (xBottomLeft - xBottomRight))
                y2 = (yBottomRight + position * (yBottomLeft - yBottomRight))
            }

            in 1.0..2.0 -> {
                x1 = xTopRight + (position - 1) * (xBottom - xTopRight)
                y1 = yTopRight + (position - 1) * (yBottom - yTopRight)

                x2 = xBottomLeft + (position - 1) * (xTop - xBottomLeft)
                y2 = yBottomLeft + (position - 1) * (yTop - yBottomLeft)
            }

            in 2.0..3.0 -> {
                x1 = xBottom + (position - 2) * (xTopLeft - xBottom)
                y1 = yBottom + (position - 2) * (yTopLeft - yBottom)

                x2 = xTop + (position - 2) * (xBottomRight - xTop)
                y2 = yTop + (position - 2) * (yBottomRight - yTop)
            }
        }

// ----------------------  UI -----------------------------------

        /* ------- To see the direction of movement, remove this section from comment mode ---------------
            drawLine(
                color = Color.Blue,
                start = Offset(x = xBottom, y = yBottom),
                end = Offset(x = xTopLeft, y = yTopLeft),
                strokeWidth = 4f
            )
            drawLine(
                color = Color.White,
                start = Offset(x = xTopLeft, y = yTopLeft),
                end = Offset(x = xTopRight, y = yTopRight),
                strokeWidth = 4f
            )
            drawLine(
                color = Color.Green,
                start = Offset(x = xTopRight, y = yTopRight),
                end = Offset(x = xBottom, y = yBottom),
                strokeWidth = 4f
            )
        */

        drawCircle(
            color = color,
            radius = diameter / 2,
            center = Offset(x = x1, y = y1)
        )


        /* ------- To see the direction of movement, remove this section from comment mode ---------------
            drawLine(
                color = Color.Blue,
                start = Offset(x = xBottomRight, y = yBottomRight),
                end = Offset(x = xTop, y = yTop),
                strokeWidth = 4f
            )
            drawLine(
                color = Color.White,
                start = Offset(x = xBottomLeft, y = yBottomLeft),
                end = Offset(x = xBottomRight, y = yBottomRight),
                strokeWidth = 4f
            )
            drawLine(
                color = Color.Green,
                start = Offset(x = xBottomLeft, y = yBottomLeft),
                end = Offset(x = xTop, y = yTop),
                strokeWidth = 4f
            )
        */

        drawCircle(
            color = color,
            radius = diameter / 2,
            center = Offset(x = x2, y = y2)
        )
    }
}