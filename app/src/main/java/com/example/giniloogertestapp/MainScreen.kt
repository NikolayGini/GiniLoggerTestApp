package com.example.giniloogertestapp

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun MainScreen(
    data: List<String>,
    onDebugClick: (String) -> Unit,
    onInfoClick: (String) -> Unit,
    onErrorClick: (String) -> Unit,
    onMultipleLogClick: (String) -> Unit,
) {
    var selectedItemIndex by remember {
        mutableIntStateOf(value = 0)
    }

    val selectedItemValue = data[selectedItemIndex]

    Column(
        Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Items(
            data = data,
            selectedItemIndex = selectedItemIndex,
            onItemClick = { index -> selectedItemIndex = index }
        )

        Buttons(
            selectedItemValue = selectedItemValue,
            onDebugClick = onDebugClick,
            onInfoClick = onInfoClick,
            onErrorClick = onErrorClick,
            onMultipleLogClick = onMultipleLogClick
        )
    }
}

@Composable
private fun ColumnScope.Items(
    data: List<String>,
    selectedItemIndex: Int,
    onItemClick: (index: Int) -> Unit,
) {
    LazyColumn(
        modifier = Modifier.weight(1f)
    ) {
        itemsIndexed(data) { index, text ->
            ItemText(
                isSelected = selectedItemIndex == index,
                value = text,
                onClick = { onItemClick(index)}
            )
            Spacer(modifier = Modifier.height(4.dp))
        }
    }
}

@Composable
private fun ItemText(isSelected: Boolean, value: String, onClick: () -> Unit) {
    val color = if (isSelected) Color(0xFFE4EC96) else Color(0xFFF1DBB6)

    Text(
        modifier = Modifier
            .fillMaxWidth()
            .background(color, shape = RoundedCornerShape(12.dp))
            .clickable { onClick() }
            .padding(8.dp)
        ,
        text = value,
    )
}

@Composable
private fun Buttons(
    selectedItemValue: String,
    onDebugClick: (String) -> Unit,
    onInfoClick: (String) -> Unit,
    onErrorClick: (String) -> Unit,
    onMultipleLogClick: (String) -> Unit,
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        LogButton(
            text = "Debug",
            color = Color(0xFF3BA0CE),
            onClick = { onDebugClick(selectedItemValue) }
        )
        LogButton(
            text = "Info",
            color = Color(0xFFECC348),
            onClick = { onInfoClick(selectedItemValue) }
        )
        LogButton(
            text = "Error",
            color = Color(0xFFCE554C),
            onClick = { onErrorClick(selectedItemValue) }
        )
        LogButton(
            text = "Multi",
            color = Color(0xFFA97EF5),
            onClick = { onMultipleLogClick(selectedItemValue) }
        )
    }
}

@Composable
private fun LogButton(text: String, color: Color, onClick: () -> Unit) {
    Button(
        colors = ButtonDefaults.buttonColors(color),
        onClick = onClick,
    ) {
        Text(text = text)
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MainScreen(
        data = listOf("first", "second", "third"),
        onDebugClick = {},
        onInfoClick = {},
        onErrorClick = {},
        onMultipleLogClick = {}
    )
}