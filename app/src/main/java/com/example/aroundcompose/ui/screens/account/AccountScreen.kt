package com.example.aroundcompose.ui.screens.account

import android.annotation.SuppressLint
import android.graphics.Paint
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.Matrix
import androidx.compose.ui.graphics.asComposePath
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.unit.dp
import androidx.graphics.shapes.CornerRounding
import androidx.graphics.shapes.RoundedPolygon
import androidx.graphics.shapes.toPath
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.aroundcompose.R
import com.example.aroundcompose.data.models.UserDTO
import com.example.aroundcompose.ui.common.views.CustomIconButton
import com.example.aroundcompose.ui.common.views.CustomTopAppBar
import com.example.aroundcompose.ui.screens.account.models.AccountEvent
import com.example.aroundcompose.ui.theme.JetAroundTheme
import com.example.aroundcompose.utils.RoundedPolygonShape

class AccountScreen(
    private val viewModel: AccountViewModel,
    private val onBackClick: () -> Unit,
    private val toSettingsScreen: () -> Unit,
    private val toStatisticScreen: () -> Unit,
    private val toFriendsScreen: () -> Unit,
    private val toMoneysScreen: () -> Unit,
    private val toSkillsScreen: () -> Unit,
    private val userId: Int,
) {
    private val isMyAccount = userId == -1

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @Composable
    fun Create() {
        val viewState by viewModel.getViewState().collectAsStateWithLifecycle()
        LaunchedEffect(key1 = Unit) { viewModel.obtainEvent(AccountEvent.GetUserInfo(userId)) }

        Scaffold(containerColor = JetAroundTheme.colors.primaryBackground) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        start = JetAroundTheme.margins.mainMargin,
                        top = JetAroundTheme.margins.mainMargin,
                        end = JetAroundTheme.margins.mainMargin,
                        bottom = 16.dp
                    ),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    CustomTopAppBar(
                        modifier = Modifier.padding(bottom = 24.dp),
                        textId = R.string.account,
                        onBackClick = onBackClick,
                        trailingIconId = if (isMyAccount) R.drawable.ic_settings else null,
                        onTrailingBtnClick = toSettingsScreen
                    )
                    Avatar(modifier = Modifier.padding(bottom = 16.dp))
                    Row(
                        modifier = Modifier.padding(bottom = 24.dp),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        NameAndLevels(
                            user = viewState.userInfo,
                            modifier = Modifier.padding(end = if (isMyAccount) 0.dp else 20.dp)
                        )
                        if (!isMyAccount) AddFriendBtn()
                    }
                    Text(
                        modifier = Modifier.padding(bottom = 16.dp),
                        text = stringResource(
                            id = if (isMyAccount) R.string.how_much_cells else R.string.other_player_how_much_cells
                        ).uppercase(),
                        style = JetAroundTheme.typography.bold16,
                        color = JetAroundTheme.colors.primary
                    )
                    MainButtons(viewState.myCells, viewState.myTeamCells, viewState.myAllTimeCells)
                    PlaceId(viewState.userInfo.id)
                }
            }
        }
    }

    @Composable
    private fun AddFriendBtn() {
        val polygon = RoundedPolygon(numVertices = 6, rounding = CornerRounding(radius = 0.14F))
        val shape = RoundedPolygonShape(polygon)
        val size = 54.dp
        val bgColor = JetAroundTheme.colors.primaryBackground
        val shadowColor = JetAroundTheme.colors.blue
        var checked by remember { mutableStateOf(false) }

        val matrix = Matrix()
        val path = polygon.toPath()
        val newSize = 75f
        matrix.scale(newSize, newSize)
        matrix.translate(1f, 1f)
        matrix.rotateZ(30F)
        path.asComposePath().transform(matrix)

        Box(modifier = Modifier
            .drawBehind {
                drawContext.canvas.nativeCanvas.apply {
                    drawPath(path, Paint().apply {
                        color = (if (checked) shadowColor else bgColor).toArgb()
                        setShadowLayer(4.dp.toPx(), 0f, 0f, shadowColor.toArgb())
                    })
                }
            }
            .size(size)
            .clip(shape)
            .clickable(interactionSource = remember { MutableInteractionSource() },
                indication = rememberRipple(),
                onClick = { checked = !checked }), contentAlignment = Alignment.Center
        ) {
            Icon(
                modifier = Modifier.size(24.dp),
                painter = painterResource(id = if (checked) R.drawable.ic_added_friend else R.drawable.ic_add_friend),
                contentDescription = "add friend",
                tint = if (checked) bgColor else shadowColor
            )
        }
    }

    @Composable
    private fun PlaceId(id: Int) {
        if (isMyAccount) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End,
            ) { Id(id) }
        } else {
            Row(
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.Bottom
            ) { Id(id) }
        }
    }

    @Composable
    private fun Id(id: Int) {
        val clipboardManager = LocalClipboardManager.current
        val context = LocalContext.current

        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = "id: $id",
                style = JetAroundTheme.typography.smallMedium,
                color = JetAroundTheme.colors.textColor
            )
            CustomIconButton(onClick = {
                clipboardManager.setText(AnnotatedString(id.toString()))
                Toast.makeText(context, R.string.copy, Toast.LENGTH_SHORT).show()
            }, iconId = R.drawable.ic_copy, description = "copy")
        }
    }

    @Composable
    private fun MainButtons(myCells: Int, teamCells: Int, myAllTimeCells: Int) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            MyAndTeamCellsCard(
                myCells = myCells,
                myTeamCells = teamCells,
                bgColor = JetAroundTheme.colors.primary,
                textColor = JetAroundTheme.colors.primaryBackground,
                modifier = Modifier.padding(bottom = 16.dp),
                onClick = toStatisticScreen
            )
            MyAllCellsCard(
                count = myAllTimeCells,
                titleTextId = R.string.all_time,
                bgColor = JetAroundTheme.colors.secondaryBackground,
                textColor = JetAroundTheme.colors.primary,
                decorationId = R.drawable.hex_decoration_2,
                modifier = Modifier.padding(bottom = 16.dp),
            )
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Start) {
                if (isMyAccount) {
                    ToOtherScreenBtn(
                        onClick = toFriendsScreen,
                        modifier = Modifier.weight(1F),
                        text = stringResource(id = R.string.friends),
                        icon = painterResource(id = R.drawable.ic_friends),
                        bgColor = JetAroundTheme.colors.lightBlue
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    ToOtherScreenBtn(
                        onClick = toMoneysScreen,
                        modifier = Modifier.weight(1F),
                        text = stringResource(id = R.string.moneys),
                        icon = painterResource(id = R.drawable.ic_menu_coin),
                        bgColor = JetAroundTheme.colors.purple
                    )
                } else {
                    ToOtherScreenBtn(
                        onClick = toSkillsScreen,
                        text = stringResource(id = R.string.skills),
                        icon = painterResource(id = R.drawable.ic_skills),
                        bgColor = JetAroundTheme.colors.purple
                    )
                }
            }
        }
    }

    @Composable
    private fun ToOtherScreenBtn(
        onClick: () -> Unit,
        text: String,
        icon: Painter,
        bgColor: Color,
        modifier: Modifier = Modifier,
    ) {
        Button(
            modifier = modifier,
            onClick = onClick,
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(containerColor = bgColor),
            elevation = ButtonDefaults.buttonElevation(4.dp),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = text,
                    color = JetAroundTheme.colors.primaryBackground,
                    style = JetAroundTheme.typography.bigMedium,
                    modifier = Modifier.padding(end = 8.dp)
                )
                Icon(
                    painter = icon,
                    contentDescription = "$text icon",
                    tint = JetAroundTheme.colors.primaryBackground
                )
            }
        }
    }

    @Composable
    private fun MyAndTeamCellsCard(
        myCells: Int,
        myTeamCells: Int,
        modifier: Modifier = Modifier,
        onClick: (() -> Unit)? = null,
        bgColor: Color = JetAroundTheme.colors.gray,
        textColor: Color = JetAroundTheme.colors.textColor,
    ) {
        Card(
            modifier = modifier
                .fillMaxWidth()
                .clickable(indication = rememberRipple(),
                    interactionSource = remember { MutableInteractionSource() },
                    enabled = onClick != null,
                    onClick = onClick ?: {}),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = bgColor),
            elevation = CardDefaults.cardElevation(4.dp)
        ) {
            Box(
                modifier = Modifier.height(70.dp), contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.hex_decoration_3),
                    contentDescription = "",
                    contentScale = ContentScale.Crop
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable(indication = rememberRipple(),
                            interactionSource = remember { MutableInteractionSource() },
                            enabled = onClick != null,
                            onClick = onClick ?: {}),
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    StatisticTextColumn(
                        myCells,
                        if (isMyAccount) R.string.you else R.string.other_player,
                        textColor,
                        Modifier.padding(16.dp)
                    )
                    StatisticTextColumn(
                        myTeamCells,
                        if (isMyAccount) R.string.your_team else R.string.other_player_team,
                        textColor,
                        Modifier.padding(16.dp)
                    )
                }
            }
        }
    }

    @Composable
    private fun MyAllCellsCard(
        count: Int,
        titleTextId: Int,
        decorationId: Int,
        modifier: Modifier = Modifier,
        onClick: (() -> Unit)? = null,
        bgColor: Color = JetAroundTheme.colors.gray,
        textColor: Color = JetAroundTheme.colors.textColor,
    ) {
        Card(
            modifier = modifier
                .fillMaxWidth()
                .clickable(indication = rememberRipple(),
                    interactionSource = remember { MutableInteractionSource() },
                    enabled = onClick != null,
                    onClick = onClick ?: {}),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = bgColor),
            elevation = CardDefaults.cardElevation(4.dp)
        ) {
            Box(
                modifier = Modifier.height(70.dp), contentAlignment = Alignment.Center
            ) {
                Image(
                    modifier = Modifier.offset(x = 60.dp),
                    painter = painterResource(id = decorationId),
                    contentDescription = "",
                    contentScale = ContentScale.Crop,
                    colorFilter = ColorFilter.tint(JetAroundTheme.colors.primary)
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable(indication = rememberRipple(),
                            interactionSource = remember { MutableInteractionSource() },
                            enabled = onClick != null,
                            onClick = onClick ?: {})
                ) {
                    StatisticTextColumn(count, titleTextId, textColor, Modifier.padding(16.dp), true)
                }
            }
        }
    }

    @Composable
    private fun StatisticTextColumn(
        count: Int,
        titleTextId: Int,
        textColor: Color,
        modifier: Modifier = Modifier,
        addCellsText: Boolean = false,
    ) {
        Column(modifier = modifier) {
            Text(
                text = stringResource(id = titleTextId),
                color = textColor,
                style = JetAroundTheme.typography.semiBold12,
                modifier = Modifier.padding(bottom = 2.dp)
            )
            Text(
                text = if (addCellsText) "$count ${stringResource(R.string.cells).uppercase()}" else "$count",
                color = textColor,
                style = JetAroundTheme.typography.bold24
            )
        }
    }

    @Composable
    private fun Avatar(modifier: Modifier = Modifier) {
        val shape = RoundedPolygonShape(
            RoundedPolygon(numVertices = 6, rounding = CornerRounding(radius = 0.05F))
        )
        Image(
            modifier = modifier
                .size(180.dp)
                .clip(shape)
                .border(4.dp, JetAroundTheme.colors.textColor, shape),
            painter = painterResource(id = R.drawable.avatar_example),
            contentDescription = "avatar"
        )
    }

    @Composable
    private fun NameAndLevels(user: UserDTO, modifier: Modifier = Modifier) {
        Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = user.username,
                color = JetAroundTheme.colors.textColor,
                style = JetAroundTheme.typography.semiBold16,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Text(
                text = "lvl.${user.level} / lvl.100",
                color = JetAroundTheme.colors.textColor,
                style = JetAroundTheme.typography.semiBold12
            )
        }
    }
}