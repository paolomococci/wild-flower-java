/**
 *
 * Copyright 2019 paolo mococci
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * 	   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package local.example.wildflower;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity
        extends AppCompatActivity {

    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    public static final String EXTRA_MESSAGE = "local.example.sunflower.extra.MESSAGE";
    private EditText editText;
    public static final int TEXT_REQUEST = 1;
    private TextView replyHeadTextView;
    private TextView replyMessageTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = findViewById(R.id.main_edit_text);
        replyHeadTextView = findViewById(R.id.header_text_from_reply);
        replyMessageTextView = findViewById(R.id.message_text_from_reply);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == TEXT_REQUEST) {
            if (resultCode == RESULT_OK) {
                String reply = data.getStringExtra(ReplyActivity.EXTRA_REPLY);
                replyHeadTextView.setVisibility(View.VISIBLE);
                replyMessageTextView.setText(reply);
                replyMessageTextView.setVisibility(View.VISIBLE);
            }
        }
    }

    public void sendToSecondActivity(View view) {
        Log.d(LOG_TAG, "Send button clicked!");
        Intent intent = new Intent(this, ReplyActivity.class);
        String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivityForResult(intent, TEXT_REQUEST);
    }
}
