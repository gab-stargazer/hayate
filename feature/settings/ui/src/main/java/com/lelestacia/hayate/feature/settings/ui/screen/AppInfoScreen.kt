package com.lelestacia.hayate.feature.settings.ui.screen

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.lelestacia.hayate.core.common.component.HayateCustomIconChip
import com.lelestacia.hayate.core.theme.AppTheme
import com.lelestacia.hayate.core.theme.padding
import com.lelestacia.hayate.core.theme.quickSandFamily
import com.lelestacia.hayate.core.theme.spacing
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Brands
import compose.icons.fontawesomeicons.brands.Github

@Composable
fun AppInfoScreen(
    isDarkTheme: Boolean,
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current
    Column(
        verticalArrangement = Arrangement.spacedBy(spacing.medium),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .padding(padding.small)
    ) {
//        Text(
//            text = "颯",
//            style = MaterialTheme.typography.headlineLarge.copy(
//                fontWeight = FontWeight.Black,
//                fontSize = 64.sp,
//                color = when (isDarkTheme) {
//                    true -> Color.White
//                    false -> MaterialTheme.colorScheme.primary
//                }
//            ),
//            textAlign = TextAlign.Center,
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(padding.extraLarge)
//        )
        Image(
            painter = painterResource(id = com.lelestacia.hayate.core.common.R.drawable.icon),
            contentDescription = null,
            modifier = Modifier
                .size(125.dp)
                .clip(RoundedCornerShape(15.dp))
        )
        Spacer(modifier = Modifier.height(spacing.medium))
        Text(
            text = "Hayate is a fast and user-friendly anime index app, powered by Kotlin and the Jikan API community. Quickly access detailed information about your favorite anime series and movies, including synopses, characters, and episodes. Perfect for both seasoned fans and newcomers, Hayate ensures a seamless and enjoyable anime browsing experience.",
            textAlign = TextAlign.Justify,
            style = MaterialTheme.typography.bodyLarge.copy(
                fontFamily = quickSandFamily,
                color = when (isDarkTheme) {
                    true -> Color.White
                    false -> MaterialTheme.colorScheme.onSurface
                }
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = padding.medium
                )
        )
        Row(
            horizontalArrangement = Arrangement.spacedBy(
                space = spacing.medium,
                alignment = Alignment.CenterHorizontally
            ),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {

            HayateCustomIconChip(
                icon = FontAwesomeIcons.Brands.Github,
                title = "Repository",
                contentDescription = null,
                isDarkTheme = isDarkTheme,
                onClick = {
                    val intent = Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("https://github.com/Kamil-Malik/hayate")
                    )
                    context.startActivity(intent)
                }
            )

//            HayateCustomIconChip(
//                icon = FontAwesomeIcons.Brands.GooglePlay,
//                title = "Google Play",
//                contentDescription = null,
//                onClick = {}
//            )
        }
    }
}

@Preview
@Composable
fun PreviewAppInfoScreen() {
    AppTheme {
        Surface {
            AppInfoScreen(isDarkTheme = false)
        }
    }
}