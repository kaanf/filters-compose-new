package com.kaanf.filtersdrawermenu.ui.component

import android.view.MotionEvent
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.unit.Dp
import kotlin.math.pow
import kotlin.math.roundToInt
import kotlin.math.sqrt

@OptIn(ExperimentalComposeUiApi::class, ExperimentalTextApi::class)
@Composable
fun PriceRanger(
    modifier: Modifier,
    rangeColor: Color,
    backColor: Color,
    barHeight: Dp,
    circleRadius: Dp,
    cornerRadius: CornerRadius,
    progress1InitialValue: Float,
    progress2InitialValue: Float,
    tooltipSpacing: Dp,
    tooltipWidth: Dp,
    tooltipHeight: Dp,
    onProgressChanged: (progress1: Float, progress2: Float) -> Unit
) {
    val circleRadiusInPx = with(LocalDensity.current) { circleRadius.toPx() }

    var firstProgress by remember {
        mutableStateOf(progress1InitialValue)
    }
    var secondProgress by remember {
        mutableStateOf(progress2InitialValue)
    }

    var width by remember {
        mutableStateOf(0f)
    }

    var height by remember {
        mutableStateOf(0f)
    }

    var leftCircleDragging by remember {
        mutableStateOf(false)
    }

    var rightCircleDragging by remember {
        mutableStateOf(false)
    }

    val leftTooltipOverlapping by remember {
        derivedStateOf { mutableStateOf(false) }
    }

    var leftCircleOffset by remember {
        mutableStateOf(Offset.Zero)
    }
    var rightCircleOffset by remember {
        mutableStateOf(Offset.Zero)
    }

    val scaleAnim1 by animateFloatAsState(
        targetValue = if (leftCircleDragging) 2f else 1f,
        animationSpec = tween(durationMillis = 300)
    )

    val scaleAnim2 by animateFloatAsState(
        targetValue = if (rightCircleDragging) 2f else 1f,
        animationSpec = tween(durationMillis = 300)
    )

    val tooltipAnim1 by animateFloatAsState(
        targetValue = if (leftTooltipOverlapping.value) -180f else 0f,
        animationSpec = tween(durationMillis = 300)
    )

    val textMeasurer = rememberTextMeasurer()

    Canvas(
        modifier = modifier
            .height(barHeight)
            .pointerInteropFilter(
                onTouchEvent = { motionEvent ->
                    when (motionEvent.action) {
                        MotionEvent.ACTION_DOWN -> {
                            val x = motionEvent.x
                            val y = motionEvent.y

                            val dis1 = sqrt(
                                (x - leftCircleOffset.x).pow(2) + (y - leftCircleOffset.y).pow(2)
                            )

                            val dis2 = sqrt(
                                (x - rightCircleOffset.x).pow(2) + (y - rightCircleOffset.y).pow(2)
                            )

                            if (dis1 < circleRadiusInPx)
                                leftCircleDragging = true
                            else if (dis2 < circleRadiusInPx)
                                rightCircleDragging = true
                        }

                        MotionEvent.ACTION_MOVE -> {
                            val x = motionEvent.x

                            if (leftCircleDragging) {
                                firstProgress = if (x <= 0)
                                    0f
                                else if (x >= width * secondProgress)
                                    secondProgress
                                else
                                    x / width

                                leftCircleOffset = leftCircleOffset.copy(x = width * firstProgress)
                            } else if (rightCircleDragging) {
                                secondProgress = if (x >= width)
                                    1f
                                else if (x <= width * firstProgress)
                                    firstProgress
                                else
                                    x / width

                                rightCircleOffset = rightCircleOffset.copy(x = width * secondProgress)
                            }
                        }

                        MotionEvent.ACTION_UP -> {
                            leftCircleDragging = false
                            rightCircleDragging = false
                            onProgressChanged(firstProgress, secondProgress)
                        }
                    }
                    true
                }
            )
            .onGloballyPositioned {
                leftCircleOffset = Offset(x = it.size.width * firstProgress, y = it.size.height / 2f)
                rightCircleOffset = Offset(x = it.size.width * secondProgress, y = it.size.height / 2f)
            }
    ) {
        width = this.size.width
        height = this.size.height

        drawRoundRect(
            color = backColor,
            cornerRadius = cornerRadius,
            topLeft = Offset(x = 0f, y = barHeight.toPx() / 4f),
            size = Size(width = width, height = barHeight.toPx() / 2f)
        )

        /**
         * Draw range line.
         */
        drawRect(
            color = rangeColor,
            topLeft = Offset(x = width * firstProgress, y = 0f),
            size = Size(width = width * (secondProgress - firstProgress), height = height)
        )

        /**
         * Draw left circle.
         */
        scale(scaleAnim1, pivot = leftCircleOffset) {
            drawCircle(
                color = rangeColor.copy(alpha = 0.2f),
                radius = circleRadius.toPx(),
                center = leftCircleOffset
            )
        }

        drawCircle(
            color = rangeColor,
            radius = circleRadius.toPx(),
            center = leftCircleOffset
        )

        /**
         * Draw right circle.
         */
        scale(scaleAnim2, pivot = rightCircleOffset) {
            drawCircle(
                color = rangeColor.copy(alpha = 0.2f),
                radius = circleRadius.toPx(),
                center = rightCircleOffset
            )
        }

        drawCircle(
            color = rangeColor,
            radius = circleRadius.toPx(),
            center = rightCircleOffset,
        )

        /**
         * Draw left price.
         */
        val leftL = leftCircleOffset.x - tooltipWidth.toPx() / 2f
        val topL =
            leftCircleOffset.y - tooltipSpacing.toPx() - circleRadiusInPx - tooltipHeight.toPx()

        val leftR = rightCircleOffset.x - tooltipWidth.toPx() / 2f
        val topR = rightCircleOffset.y - tooltipSpacing.toPx() - circleRadiusInPx - tooltipHeight.toPx()

        if (leftCircleDragging || rightCircleDragging)
            leftTooltipOverlapping.value = (leftL + tooltipWidth.toPx()) >= leftR

        val textLeft = (firstProgress * 100).roundToInt().toString() + "₺"
        var textLayoutResult = textMeasurer.measure(
            text = AnnotatedString(textLeft),
            style = TextStyle(color = Color(0xfffa5373))
        )
        var textSize = textLayoutResult.size

        rotate(tooltipAnim1, pivot = leftCircleOffset) {
            drawText(
                textLayoutResult = textLayoutResult,
                topLeft = Offset(
                    x = leftL + tooltipWidth.toPx() / 2 - textSize.width / 2,
                    y = topL + tooltipHeight.toPx() / 2 - textSize.height / 2
                )
            )
        }

        /**
         * Draw right price.
         */
        val textRight = (secondProgress * 100).roundToInt().toString() + "₺"
                textLayoutResult = textMeasurer.measure(
            text = AnnotatedString(textRight),
            style = TextStyle(color = Color(0xfffa5373))
        )
        textSize = textLayoutResult.size

        drawText(
            textLayoutResult = textLayoutResult,
            topLeft = Offset(
                x = leftR + tooltipWidth.toPx() / 2 - textSize.width / 2,
                y = topR + tooltipHeight.toPx() / 2 - textSize.height / 2
            ),
        )
    }
}