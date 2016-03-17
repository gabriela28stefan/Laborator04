package ro.pub.cs.systems.pdsd.lab04.contactsmanager;

import java.util.ArrayList;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class ContactsManagerActivity extends Activity {

	private ContactsListener contactsListener = new ContactsListener();
	
	public class ContactsListener implements View.OnClickListener {

		@Override
		public void onClick(View v) {
			
			switch (v.getId()) {
			case R.id.showAdditionalFields:
			
				break;
			case R.id.save_button:
				EditText editName = (EditText)findViewById(R.id.name);
				String name = editName.getText().toString();
				
				EditText editTelephone = (EditText)findViewById(R.id.telephone);
				String telephone = editTelephone.getText().toString();
				
				EditText editEmail = (EditText)findViewById(R.id.email);
				String email = editEmail.getText().toString();
				
				EditText editAddress = (EditText)findViewById(R.id.address);
				String address = editAddress.getText().toString();
				
				String jobTitle = null;
				String company = null;
				String webSite = null;
				String IM = null;
				
				EditText editJobTitle = (EditText)findViewById(R.id.job);
				if(editJobTitle != null){
					jobTitle = editJobTitle.getText().toString();
				}
				
				EditText editCompany = (EditText)findViewById(R.id.company);
				if(editCompany != null){
					company = editCompany.getText().toString();
				}
				
				EditText editWebsite = (EditText)findViewById(R.id.website);
				if(editWebsite != null){
					webSite = editWebsite.getText().toString();
				}
				
				EditText editIM = (EditText)findViewById(R.id.IM);
				if(editIM != null){
					IM = editIM.getText().toString();
				}
				
				Intent intent = new Intent(ContactsContract.Intents.Insert.ACTION);
				intent.setType(ContactsContract.RawContacts.CONTENT_TYPE);
				if (name != null) {
				  intent.putExtra(ContactsContract.Intents.Insert.NAME, name);
				}
				if (telephone != null) {
				  intent.putExtra(ContactsContract.Intents.Insert.PHONE, telephone);
				}
				if (email != null) {
				  intent.putExtra(ContactsContract.Intents.Insert.EMAIL, email);
				}
				if (address != null) {
				  intent.putExtra(ContactsContract.Intents.Insert.POSTAL, address);
				}
				if (jobTitle != null) {
				  intent.putExtra(ContactsContract.Intents.Insert.JOB_TITLE, jobTitle);
				}
				if (company != null) {
				  intent.putExtra(ContactsContract.Intents.Insert.COMPANY, company);
				}
				ArrayList<ContentValues> contactData = new ArrayList<ContentValues>();
				if (webSite != null) {
				  ContentValues websiteRow = new ContentValues();
				  websiteRow.put(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Website.CONTENT_ITEM_TYPE);
				  websiteRow.put(ContactsContract.CommonDataKinds.Website.URL, webSite);
				  contactData.add(websiteRow);
				}
				if (IM != null) {
				  ContentValues imRow = new ContentValues();
				  imRow.put(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Im.CONTENT_ITEM_TYPE);
				  imRow.put(ContactsContract.CommonDataKinds.Im.DATA, IM);
				  contactData.add(imRow);
				}
				intent.putParcelableArrayListExtra(ContactsContract.Intents.Insert.DATA, contactData);
				startActivity(intent);
				break;
			case R.id.cancel_button: finish();
				break;
			default:
				break;
			}
			
		}
		
	}
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts_manager);
        Button additionalfields = (Button) findViewById(R.id.showAdditionalFields);
        Button saveButton = (Button) findViewById(R.id.save_button);
        Button cancelButton = (Button) findViewById(R.id.cancel_button);
        
        additionalfields.setOnClickListener(contactsListener);
        saveButton.setOnClickListener(contactsListener);
        cancelButton.setOnClickListener(contactsListener);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.contacts_manager, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
