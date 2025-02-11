package com.example.mobilesmartpiggy

import android.content.Intent
import android.nfc.NfcAdapter
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Room
import com.example.mobilesmartpiggy.ui.theme.MobileSmartPiggyTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

class MainActivity : ComponentActivity() {

    private val db by lazy {
        Room.databaseBuilder(
            applicationContext,
            PigDatabase::class.java,
            name = "pigs.db"
        ).createFromAsset("pigs.db")
            .build()
    }
    private val viewModel by viewModels<PigsViewModel>(
        factoryProducer = {
            object: ViewModelProvider.Factory {
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return PigsViewModel(db.dao) as T
                }
             }
        }
    )
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
//        setContent {
//            MobileSmartPiggyTheme {
//                    val state by viewModel.state.collectAsState()
//                    PigsScreen(state = state, onEvent = viewModel::onEvent)
//                }
//            }
//        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val db by lazy {
            Room.databaseBuilder(
                applicationContext,
                PigDatabase::class.java,
                name = "pigs.db"
            ).createFromAsset("pigs.db")
                .build()
        }
        val pigDao = db.dao;
        val pig: Flow<Pigs> = pigDao.getPigsById(129006)
        val pigId: Int
        runBlocking(Dispatchers.IO) {
            pigId = pig.first().pigId
        }
        setContent {
            MobileSmartPiggyTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = pigId.toString(),
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
//    override fun onNewIntent(intent: Intent) {
//        super.onNewIntent(intent)
//        if (NfcAdapter.ACTION_NDEF_DISCOVERED == intent.action) {
//            intent.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES)?.also { rawMessages ->
//                val messages: List<NdefMessage> = rawMessages.map { it as NdefMessage }
//                // Process the messages array.
//            }
//        }
//    }
}

@Composable
    fun Greeting(name: String, modifier: Modifier = Modifier) {
//    val db by lazy {
//        Room.databaseBuilder(
//            applicationContext,
//            PigDatabase::class.java,
//            name = "pigs.db"
//        ).createFromAsset("pigs.db")
//            .build()
//    }
//    val pigDao = db.dao;
//    val pig: Flow<Pigs> = pigDao.getPigsById(129006)
//    val pigId: Int
//    runBlocking(Dispatchers.IO) {
//        pigId = pig.first().pigId
//    }
        Text(
            text = "Hello $name!",
            modifier = modifier
        )
    }

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MobileSmartPiggyTheme {
        Greeting("Android")
    }
}
// @Override
// 	    protected void onNewIntent(Intent intent){
// 	        if(NfcAdapter.ACTION_TAG_DISCOVERED.equals(intent.getAction())){
// 	        mytag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);  // get the detected tag
// 	        Parcelable[] msgs =
// 	intent.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES);
// 	            NdefRecord firstRecord = ((NdefMessage)msgs[0]).getRecords()[0];
// 	            byte[] payload = firstRecord.getPayload();
// 	            int payloadLength = payload.length;
// 	            int langLength = payload[0];
// 	            int textLength = payloadLength - langLength - 1;
// 	            byte[] text = new byte[textLength];
// 	            System.arraycopy(payload, 1+langLength, text, 0, textLength);
// 	            Toast.makeText(this, this.getString(R.string.ok_detection)+new String(text), Toast.LENGTH_LONG).show();
// 	                    }
// 	    }