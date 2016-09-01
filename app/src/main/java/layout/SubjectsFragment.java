package layout;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.util.Log;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import br.com.dina.ui.widget.UITableView;
import br.com.dina.ui.widget.UITableView.ClickListener;

import tech.rigo.pi.R;
import tech.rigo.pi.SignUpActivity;
import tech.rigo.pi.Subject;
import tech.rigo.pi.SubjectsAdapter;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

import org.json.*;

public class SubjectsFragment extends Fragment {
    public SubjectsFragment() {
        // Required empty public constructor
    }
    
    private ListView mListView;
    
    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = getResources().openRawResource(R.raw.data);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_subjects, container, false);

        mListView = (ListView) rootView.findViewById(R.id.subjects_list_view);
        
        ArrayList<Subject> subjects = new ArrayList<Subject>();
        
        try {
            JSONObject obj = new JSONObject(loadJSONFromAsset());
            JSONArray m_jArry = obj.getJSONArray("subjects");
            Subject subject;
            
            for (int i = 0; i < m_jArry.length(); i++) {
                JSONObject jo_inside = m_jArry.getJSONObject(i);
                Log.d("Subject-->", jo_inside.getString("subject_name"));
                String subject_name = jo_inside.getString("subject_name");
                
                //Add your values in your `ArrayList` as below:
                subject = new Subject();
                subject.setName(subject_name);
                subject.setFormulaCount(10); // Test
                
                subjects.add(subject);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        
        mListView.setAdapter(new SubjectsAdapter(getActivity(), subjects));
        
        // ListView Item Click Listener
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                // ListView Clicked item index
                int itemPosition = position;

                // ListView Clicked item value
                Subject itemValue = (Subject) mListView.getItemAtPosition(position);
                String subjectValue = itemValue.getName() + " - " + itemValue.getFormulaCount();
                
                // Show Alert 
                Toast.makeText(getActivity(),
                        "Position :" + itemPosition + "  ListItem : " + subjectValue, Toast.LENGTH_SHORT)
                        .show();

            }
        });
        
        return rootView;
    }
}
