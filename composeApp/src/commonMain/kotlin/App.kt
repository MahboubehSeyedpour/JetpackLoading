import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.spr.jetpack_loading.enums.PunchType
import com.spr.jetpack_loading.components.indicators.lineScaleIndicator.LineScaleIndicator
import com.spr.jetpack_loading.components.indicators.BallClipRotateMultipleIndicator
import com.spr.jetpack_loading.components.indicators.BallClipRotatePulseIndicator
import com.spr.jetpack_loading.components.indicators.BallPulseRiseIndicator
import com.spr.jetpack_loading.components.indicators.BallPulseSyncIndicator
import com.spr.jetpack_loading.components.indicators.BallRotateIndicator
import com.spr.jetpack_loading.components.indicators.BallScaleIndicator
import com.spr.jetpack_loading.components.indicators.BallScaleMultipleIndicator
import com.spr.jetpack_loading.components.indicators.BallScaleRippleIndicator
import com.spr.jetpack_loading.components.indicators.BallScaleRippleMultipleIndicator
import com.spr.jetpack_loading.components.indicators.BallSpinFadeLoaderIndicator
import com.spr.jetpack_loading.components.indicators.BallTrianglePathIndicator
import com.spr.jetpack_loading.components.indicators.BallZigZagDeflectIndicator
import com.spr.jetpack_loading.components.indicators.BallZigZagIndicator
import com.spr.jetpack_loading.components.indicators.CircularPulsatingIndicator
import com.spr.jetpack_loading.components.indicators.CubeTransitionIndicator
import com.spr.jetpack_loading.components.indicators.LineSpinFadeLoaderIndicator
import com.spr.jetpack_loading.components.indicators.PacmanIndicator
import com.spr.jetpack_loading.components.indicators.PulsatingDot
import com.spr.jetpack_loading.components.indicators.SemiCircleSpinIndicator
import com.spr.jetpack_loading.components.indicators.SquareSpinIndicator
import com.spr.jetpack_loading.components.indicators.TriangleSpinIndicator
import com.spr.jetpack_loading.components.indicators.BallBeatIndicator
import com.spr.jetpack_loading.components.indicators.gridIndicator.GridIndicator
import com.spr.jetpack_loading.enums.GridAnimationType

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun App() = Surface {
    val rowHeight: Dp = 50.dp

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Blue),
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        item {
            // ------------------ Row #1 ----------------------
            Row(
                modifier = Modifier
                    .height(rowHeight)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                PulsatingDot()
                GridIndicator(animationType = GridAnimationType.PULSATING)
                CircularPulsatingIndicator()
                BallClipRotatePulseIndicator()
            }
        }

        item {
            // ------------------ Row #2 ----------------------
            Row(
                modifier = Modifier
                    .height(rowHeight)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                SquareSpinIndicator()
                BallClipRotateMultipleIndicator()
                BallPulseRiseIndicator()
                BallRotateIndicator()
            }
        }


        item {
            // ------------------ Row #3 ----------------------
            Row(
                modifier = Modifier
                    .height(rowHeight)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                CubeTransitionIndicator()
                BallZigZagIndicator()
                BallZigZagDeflectIndicator()
                BallTrianglePathIndicator()
            }
        }

        item {
            // ------------------ Row #4 ----------------------
            Row(
                modifier = Modifier
                    .height(rowHeight)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                BallScaleIndicator()
                LineScaleIndicator(punchType = PunchType.ACCORDION_PUNCH)
                LineScaleIndicator(punchType = PunchType.RANDOM_PUNCH)
                BallScaleMultipleIndicator()
            }
        }


        item {
            // ------------------ Row #5 ----------------------
            Row(
                modifier = Modifier
                    .height(rowHeight)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                BallPulseSyncIndicator()
                BallBeatIndicator()
                LineScaleIndicator(punchType = PunchType.SYMMETRIC_PUNCH)
                LineScaleIndicator(punchType = PunchType.PULSE_OUT_PUNCH)
            }
        }


        item {
            // ------------------ Row #6 ----------------------
            Row(
                modifier = Modifier
                    .height(rowHeight)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                BallScaleRippleIndicator()
                BallScaleRippleMultipleIndicator()
                BallSpinFadeLoaderIndicator()
                LineSpinFadeLoaderIndicator()
            }
        }


        item {
            // ------------------ Row #7 ----------------------
            Row(
                modifier = Modifier
                    .height(rowHeight)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                TriangleSpinIndicator()
                PacmanIndicator()
                GridIndicator(animationType = GridAnimationType.BEATING)


                // https://github.com/JetBrains/compose-multiplatform/discussions/3225
                SemiCircleSpinIndicator(canvasSize = (LocalWindowInfo.current.containerSize.width.toDouble() / 9.toDouble()).dp)
            }
        }
    }
}