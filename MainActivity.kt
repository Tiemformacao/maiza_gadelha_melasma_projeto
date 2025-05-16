package com.example.maizamelasma

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.maizamelasma.ui.theme.MaizaMelasmaTheme

data class Pedido(val nomeCliente: String, val produto: String, val status: String)

data class Produto(val nome: String, val quantidade: Int)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaizaMelasmaTheme {
                AppContent()
            }
        }
    }
}

@Composable
fun AppContent() {
    var telaAtual by remember { mutableStateOf("pedidos") }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("App da Maiza") })
        },
        bottomBar = {
            BottomAppBar {
                Button(onClick = { telaAtual = "pedidos" }, modifier = Modifier.weight(1f)) {
                    Text("Pedidos")
                }
                Button(onClick = { telaAtual = "produtos" }, modifier = Modifier.weight(1f)) {
                    Text("Estoque")
                }
            }
        }
    ) { padding ->
        Column(modifier = Modifier.padding(padding)) {
            when (telaAtual) {
                "pedidos" -> TelaPedidos()
                "produtos" -> TelaProdutos()
            }
        }
    }
}

@Composable
fun TelaPedidos() {
    val pedidos = listOf(
        Pedido("Ana", "Sabonete Clareador", "Enviado"),
        Pedido("Carla", "Sérum Noturno", "Pendente"),
        Pedido("Fernanda", "Kit Tratamento", "Entregue")
    )

    LazyColumn(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        items(pedidos.size) { index ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("Cliente: ${pedidos[index].nomeCliente}")
                    Text("Produto: ${pedidos[index].produto}")
                    Text("Status: ${pedidos[index].status}")
                }
            }
        }
    }
}

@Composable
fun TelaProdutos() {
    val produtos = listOf(
        Produto("Sabonete Clareador", 10),
        Produto("Sérum Noturno", 5),
        Produto("Protetor Solar", 2)
    )

    LazyColumn(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        items(produtos.size) { index ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("Produto: ${produtos[index].nome}")
                    Text("Quantidade: ${produtos[index].quantidade}")
                }
            }
        }
    }
}
