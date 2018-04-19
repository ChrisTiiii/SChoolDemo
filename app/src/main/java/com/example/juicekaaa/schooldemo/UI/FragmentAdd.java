package com.example.juicekaaa.schooldemo.UI;

import android.app.Fragment;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View.OnClickListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.juicekaaa.schooldemo.Data.MyDBHelper;
import com.example.juicekaaa.schooldemo.R;

/**
 * Created by Juicekaaa on 16/10/17.
 */
public class FragmentAdd extends Fragment implements OnClickListener {
    private View view;
    private Button bt_save, bt_delete;
    private EditText et_name, et_uuid, et_major, et_minor, et_x, et_y;
    private SQLiteDatabase db = null;


    public FragmentAdd() {

    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.frag_add, container, false);

        et_name = (EditText) view.findViewById(R.id.add_et_name);
        et_uuid = (EditText) view.findViewById(R.id.add_et_UUID);
        et_major = (EditText) view.findViewById(R.id.add_et_Major);
        et_minor = (EditText) view.findViewById(R.id.add_et_Minor);
        et_x = (EditText) view.findViewById(R.id.add_et_X);
        et_y = (EditText) view.findViewById(R.id.add_et_Y);

        bt_save = (Button) view.findViewById(R.id.add_button_save);
        bt_delete = (Button) view.findViewById(R.id.add_button_delete);
        bt_save.setOnClickListener(this);
        bt_delete.setOnClickListener(this);

        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);

    }

    @Override
    public void onClick(View view) {

        MyDBHelper helper = new MyDBHelper(getActivity());
        db = helper.getWritableDatabase();
        switch (view.getId()) {

            case R.id.add_button_save:
                if (et_name.getText().toString().trim().length() != 0 && et_uuid.getText().toString().trim().length() != 0
                        && et_major.getText().toString().trim().length() != 0 && et_minor.getText().toString().trim().length() != 0
                        && et_x.getText().toString().trim().length() != 0 && et_y.getText().toString().trim().length() != 0) {
                    try {
                        String sql = "INSERT INTO equipment(equipment_name,equipment_uuid,equipment_major,equipment_minor,equipment_x,equipment_y) VALUES ('" + et_name.getText() + "','" + et_uuid.getText() + "','" + et_major.getText() + "','" + et_minor.getText() + "','" + et_x.getText() + "','" + et_y.getText() + "')";
                        db.execSQL(sql);

                        Toast.makeText(getActivity(), "保存成功!", Toast.LENGTH_LONG).show();
                        et_name.setText("");
                        et_uuid.setText("");
                        et_major.setText("");
                        et_minor.setText("");
                        et_x.setText("");
                        et_y.setText("");
                    } catch (Exception e) {
                        Toast.makeText(getActivity(), "保存失败！", Toast.LENGTH_LONG).show();
                    }
//                   db.close();

                } else {
                    Toast.makeText(getActivity(), "注册项目不能为空", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.add_button_delete:
                et_name.setText("");
                et_uuid.setText("");
                et_major.setText("");
                et_minor.setText("");
                et_x.setText("");
                et_y.setText("");
                Toast.makeText(getActivity(), "清除成功！", Toast.LENGTH_LONG).show();
                break;

        }


    }


}
