package com.example.a16september.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.a16september.ui.loadNakshatras
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@Composable
fun NakshatraScreen() {
    val context = LocalContext.current
    
    // Using produceState to load data asynchronously off the main thread
    val nakshatrasState = produceState(initialValue = emptyList()) {
        value = withContext(Dispatchers.IO) {
            loadNakshatras(context)
        }
    }
    
    val nakshatras = nakshatrasState.value

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .safeDrawingPadding() // Fix for edge-to-edge content overlap
            .padding(12.dp)
    ) {
        items(nakshatras, key = { it.id }) { n ->
            Card(modifier = Modifier.fillMaxWidth().padding(vertical = 6.dp)) {
                Column(modifier = Modifier.padding(12.dp)) {
                    Text("${n.id}. ${n.name}", style = MaterialTheme.typography.titleMedium)
                    Text("Ruler: ${n.ruler} | Deity: ${n.deity}", style = MaterialTheme.typography.bodyMedium)
                    Text("Symbol: ${n.symbol}", style = MaterialTheme.typography.bodyMedium)
                    Text(n.description, style = MaterialTheme.typography.bodySmall)
                }
            }
        }
    }
}
