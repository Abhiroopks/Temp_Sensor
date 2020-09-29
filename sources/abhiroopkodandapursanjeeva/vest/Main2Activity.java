package abhiroopkodandapursanjeeva.vest;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.p003v7.app.AlertDialog;
import android.support.p003v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Set;
import java.util.UUID;

public class Main2Activity extends AppCompatActivity {
    private static final UUID BTUUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
    /* access modifiers changed from: private */
    public BluetoothAdapter BTAdapt;
    /* access modifiers changed from: private */
    public ArrayAdapter<String> BTArrayAdapter;
    private ListView BTListDevices;
    private Button BTRange;
    final BroadcastReceiver BTReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            if ("android.bluetooth.device.action.FOUND".equals(intent.getAction())) {
                BluetoothDevice device = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
                ArrayAdapter access$700 = Main2Activity.this.BTArrayAdapter;
                access$700.add(device.getName() + "\n" + device.getAddress());
            }
        }
    };
    /* access modifiers changed from: private */
    public BluetoothSocket BTSock = null;
    /* access modifiers changed from: private */
    public TextView BTStatus;
    /* access modifiers changed from: private */
    public ConnectedThread ConnectedThreadObj;
    /* access modifiers changed from: private */
    public TextView DataBuffer;
    private AdapterView.OnItemClickListener DeviceClickListener = new AdapterView.OnItemClickListener() {
        public void onItemClick(AdapterView<?> adapterView, View v, int arg2, long arg3) {
            if (!Main2Activity.this.BTAdapt.isEnabled()) {
                Toast.makeText(Main2Activity.this.getBaseContext(), "Turn Bluetooth On First", 0).show();
                return;
            }
            Main2Activity.this.BTStatus.setText("Attempting to Connect...");
            String info = ((TextView) v).getText().toString();
            final String name = info.substring(0, info.length() - 17);
            final String address = info.substring(info.length() - 17);
            new Thread() {
                public void run() {
                    boolean f = false;
                    try {
                        BluetoothSocket unused = Main2Activity.this.BTSock = Main2Activity.this.createBTSock(Main2Activity.this.BTAdapt.getRemoteDevice(address));
                    } catch (IOException e) {
                        f = true;
                        Toast.makeText(Main2Activity.this.getBaseContext(), "Data Pipe Creation Failed", 0).show();
                    }
                    try {
                        Main2Activity.this.BTSock.connect();
                    } catch (IOException e2) {
                        f = true;
                        try {
                            Main2Activity.this.BTSock.close();
                            Main2Activity.this.MainHandler.obtainMessage(3, -1, -1).sendToTarget();
                        } catch (IOException e3) {
                            Toast.makeText(Main2Activity.this.getBaseContext(), "Data Pipe Creation Failed", 0).show();
                        }
                    }
                    if (!f) {
                        ConnectedThread unused2 = Main2Activity.this.ConnectedThreadObj = new ConnectedThread(Main2Activity.this.BTSock);
                        Main2Activity.this.ConnectedThreadObj.start();
                        Main2Activity.this.MainHandler.obtainMessage(3, 1, -1, name).sendToTarget();
                    }
                }
            }.start();
        }
    };
    private Button FindBT;
    private Button History;
    /* access modifiers changed from: private */
    public Handler MainHandler;
    private Button OffBT;
    private Button OnBT;
    private Set<BluetoothDevice> PairedDevices;
    public double TMaxC = 37.2d;
    public double TMaxCap = 37.5d;
    public double TMinC = 36.1d;
    public double TMinCap = 36.0d;
    /* access modifiers changed from: private */
    public ToggleButton UnitButton;
    private Button ViewPairedDevices;
    public int i_array = -1;
    public double[] tempCArray = new double[900];
    public double[] tempFArray = new double[900];

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) C0010R.layout.activity_main2);
        this.BTStatus = (TextView) findViewById(C0010R.C0012id.BTStatus);
        this.DataBuffer = (TextView) findViewById(C0010R.C0012id.DataView);
        this.OnBT = (Button) findViewById(C0010R.C0012id.BTOn);
        this.OffBT = (Button) findViewById(C0010R.C0012id.BTOff);
        this.FindBT = (Button) findViewById(C0010R.C0012id.ScanNewDevices);
        this.ViewPairedDevices = (Button) findViewById(C0010R.C0012id.ViewPairedDevices);
        this.UnitButton = (ToggleButton) findViewById(C0010R.C0012id.UnitButton);
        this.BTRange = (Button) findViewById(C0010R.C0012id.BTRange);
        this.History = (Button) findViewById(C0010R.C0012id.BTGraph);
        final MediaPlayer mpHot = MediaPlayer.create(this, C0010R.raw.alarm);
        final MediaPlayer mpCold = MediaPlayer.create(this, C0010R.raw.alarmcold);
        this.BTArrayAdapter = new ArrayAdapter<>(this, 17367043);
        this.BTAdapt = BluetoothAdapter.getDefaultAdapter();
        this.BTListDevices = (ListView) findViewById(C0010R.C0012id.BTDeviceList);
        this.BTListDevices.setAdapter(this.BTArrayAdapter);
        this.BTListDevices.setOnItemClickListener(this.DeviceClickListener);
        this.MainHandler = new Handler() {
            public void handleMessage(Message msg) {
                if (msg.what == 2) {
                    String readMessage = null;
                    try {
                        readMessage = new String((byte[]) msg.obj, "ASCII");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    double readTempC = Double.parseDouble(readMessage) + 8.4d;
                    double readTempF = ((9.0d * readTempC) / 5.0d) + 32.0d;
                    Main2Activity.this.i_array++;
                    Main2Activity.this.i_array %= 720;
                    Main2Activity.this.tempCArray[Main2Activity.this.i_array] = readTempC;
                    Main2Activity.this.tempFArray[Main2Activity.this.i_array] = readTempF;
                    if (Main2Activity.this.UnitButton.isChecked()) {
                        Main2Activity.this.DataBuffer.setText(String.format("%.2f F", new Object[]{Double.valueOf(readTempF)}));
                    } else {
                        Main2Activity.this.DataBuffer.setText(String.format("%.2f C", new Object[]{Double.valueOf(readTempC)}));
                    }
                    if (readTempC > Main2Activity.this.TMaxC) {
                        mpHot.start();
                    } else if (mpHot.isPlaying()) {
                        mpHot.pause();
                    }
                    if (readTempC < Main2Activity.this.TMinC) {
                        mpCold.start();
                    } else if (mpCold.isPlaying()) {
                        mpCold.pause();
                    }
                }
                if (msg.what != 3) {
                    return;
                }
                if (msg.arg1 == 1) {
                    Main2Activity.this.BTStatus.setText("Connected to Device: " + ((String) msg.obj));
                    return;
                }
                Main2Activity.this.BTStatus.setText("No Connection");
            }
        };
        if (this.BTArrayAdapter == null) {
            this.BTStatus.setText("Bluetooth Not Found");
            return;
        }
        this.OnBT.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Main2Activity.this.BTOn(v);
            }
        });
        this.OffBT.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Main2Activity.this.BTOff(v);
            }
        });
        this.ViewPairedDevices.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Main2Activity.this.viewPairedDevices(v);
            }
        });
        this.FindBT.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Main2Activity.this.findBT(v);
            }
        });
        this.BTRange.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(Main2Activity.this);
                View mView = Main2Activity.this.getLayoutInflater().inflate(C0010R.layout.dialog_range, (ViewGroup) null);
                final EditText maxEdit = (EditText) mView.findViewById(C0010R.C0012id.Max_Temp_Edit);
                final EditText minEdit = (EditText) mView.findViewById(C0010R.C0012id.Min_Temp_Edit);
                ((Button) mView.findViewById(C0010R.C0012id.BT_Submit_Range)).setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        Double tempMax = Double.valueOf(Main2Activity.this.TMaxC);
                        Double tempMin = Double.valueOf(Main2Activity.this.TMinC);
                        String maxStr = maxEdit.getText().toString();
                        String minStr = minEdit.getText().toString();
                        if (!maxStr.isEmpty()) {
                            tempMax = Double.valueOf(Double.parseDouble(maxStr));
                        }
                        if (!minStr.isEmpty()) {
                            tempMin = Double.valueOf(Double.parseDouble(minStr));
                        }
                        if (Main2Activity.this.UnitButton.isChecked()) {
                            if (!maxStr.isEmpty()) {
                                tempMax = Double.valueOf(((tempMax.doubleValue() - 32.0d) * 5.0d) / 9.0d);
                            }
                            if (!minStr.isEmpty()) {
                                tempMin = Double.valueOf(((tempMin.doubleValue() - 32.0d) * 5.0d) / 9.0d);
                            }
                        }
                        if (tempMax.doubleValue() > Main2Activity.this.TMaxCap || tempMin.doubleValue() < Main2Activity.this.TMinCap) {
                            Toast.makeText(Main2Activity.this.getApplicationContext(), "Please use an appropriate range ", 0).show();
                            return;
                        }
                        Main2Activity.this.TMaxC = tempMax.doubleValue();
                        Main2Activity.this.TMinC = tempMin.doubleValue();
                    }
                });
                mBuilder.setView(mView);
                mBuilder.create().show();
            }
        });
    }

    /* access modifiers changed from: private */
    public void BTOn(View view) {
        if (!this.BTAdapt.isEnabled()) {
            startActivityForResult(new Intent("android.bluetooth.adapter.action.REQUEST_ENABLE"), 1);
            this.BTStatus.setText("Bluetooth is Enabled");
            return;
        }
        Toast.makeText(getApplicationContext(), "Bluetooth is Already Enabled", 0).show();
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int requestCode, int resultCode, Intent Data) {
        if (requestCode != 1) {
            return;
        }
        if (resultCode == -1) {
            this.BTStatus.setText("Bluetooth is Enabled");
        } else {
            this.BTStatus.setText("Bluetooth is Disabled");
        }
    }

    /* access modifiers changed from: private */
    public void BTOff(View view) {
        this.BTAdapt.disable();
        this.BTStatus.setText("Bluetooth is Disabled");
        this.BTArrayAdapter.clear();
        Toast.makeText(getApplicationContext(), "Bluetooth Turned Off", 0).show();
    }

    /* access modifiers changed from: private */
    public void findBT(View view) {
        if (this.BTAdapt.isDiscovering()) {
            this.BTAdapt.cancelDiscovery();
            Toast.makeText(getApplicationContext(), "Scanning Stopped", 0).show();
        } else if (this.BTAdapt.isEnabled()) {
            this.BTArrayAdapter.clear();
            this.BTAdapt.startDiscovery();
            Toast.makeText(getApplicationContext(), "Scan Started", 0).show();
            registerReceiver(this.BTReceiver, new IntentFilter("android.bluetooth.device.action.FOUND"));
        } else {
            Toast.makeText(getApplicationContext(), "Please Turn Bluetooth On First", 0).show();
        }
    }

    /* access modifiers changed from: private */
    public void viewPairedDevices(View view) {
        this.PairedDevices = this.BTAdapt.getBondedDevices();
        if (this.BTAdapt.isEnabled()) {
            for (BluetoothDevice device : this.PairedDevices) {
                ArrayAdapter<String> arrayAdapter = this.BTArrayAdapter;
                arrayAdapter.add(device.getName() + "\n" + device.getAddress());
            }
            return;
        }
        Toast.makeText(getApplicationContext(), "Please Turn Bluetooth On First", 0).show();
    }

    /* access modifiers changed from: private */
    public BluetoothSocket createBTSock(BluetoothDevice device) throws IOException {
        return device.createRfcommSocketToServiceRecord(BTUUID);
    }

    class ConnectedThread extends Thread {
        private final InputStream mmInStream;
        private final BluetoothSocket mmSocket;

        public ConnectedThread(BluetoothSocket socket) {
            this.mmSocket = socket;
            InputStream tmpIn = null;
            try {
                tmpIn = socket.getInputStream();
            } catch (IOException e) {
            }
            this.mmInStream = tmpIn;
        }

        public void run() {
            while (true) {
                try {
                    int bytes = this.mmInStream.available();
                    if (bytes > 5) {
                        this.mmInStream.skip((long) bytes);
                    }
                    if (bytes == 5) {
                        byte[] bytebuff = new byte[bytes];
                        SystemClock.sleep(2000);
                        Main2Activity.this.MainHandler.obtainMessage(2, this.mmInStream.read(bytebuff, 0, bytes), -1, bytebuff).sendToTarget();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
            }
        }

        public void cancel() {
            try {
                this.mmSocket.close();
            } catch (IOException e) {
            }
        }
    }

    public void openGraph(View view) {
        Intent i = new Intent(getApplicationContext(), activity_graph.class);
        if (this.UnitButton.isChecked()) {
            i.putExtra("data", this.tempFArray);
            i.putExtra("unit", true);
            i.putExtra("size", this.i_array);
            startActivity(i);
            return;
        }
        i.putExtra("data", this.tempCArray);
        i.putExtra("unit", false);
        i.putExtra("size", this.i_array);
        startActivity(i);
    }
}
