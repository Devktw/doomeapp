package com.mstudio.android.doome.app.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v7.widget.Toolbar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import android.content.Intent;

import android.widget.Toast;

import android.widget.ImageView;
import android.content.Context;
import com.bumptech.glide.Glide;
import android.annotation.NonNull;
import android.view.View.OnClickListener;
import android.app.ProgressDialog;
import java.lang.ref.Reference;
import android.os.Handler;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.content.SharedPreferences;
import android.app.Activity;
import android.content.res.Configuration;
import com.mstudio.android.doome.app.R;
import com.mstudio.android.doome.app.activity.theme;
import com.mstudio.android.doome.app.activity.about;
import com.mstudio.android.doome.app.main;
public class profile extends Fragment {
	private TextView mSign;
    public static final int RC_SIGN_IN = 1;
  //  public FirebaseAuth mAuth;
    //GoogleSignInClient mGoogleSignInClient;
	private Intent next = new Intent();
    private TextView mOut;
	private TextView username;
	private ImageView profile;
	private FrameLayout edit_profile;
	private RelativeLayout about;
	Context mcontext;
	private RelativeLayout theme;
	private static profile instance;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		instance = this;
		getActivity().setTheme(R.style.AppTheme);
		final View view = inflater.inflate(R.layout.profile, container, false);
		//com.google.firebase.FirebaseApp.initializeApp(getActivity());
		//mAuth = FirebaseAuth.getInstance();
		about = view.findViewById(R.id.about);
		//theme = view.findViewById(R.id.theme);
		username = view.findViewById(R.id.username);
		profile = view.findViewById(R.id.profile_login);
		mSign=view.findViewById(R.id.login);
		mOut=view.findViewById(R.id.logout);
		edit_profile=view.findViewById(R.id.edit_profile);
		
		about.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View p1) {
					Intent goabout = new Intent (getActivity(),about.class);
					goabout.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
					startActivity(goabout);					
				}
		});
		//theme.setOnClickListener(new OnClickListener(){

			//	@Override
				//public void onClick(View p1) {
				//	Intent gotheme = new Intent (getActivity(),theme.class);
					//gotheme.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
					///startActivity(gotheme);					
				//}
			////});
		
		
		
		
		
      //  GoogleSignInOptions googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
    //        .requestIdToken(getString(R.string.default_web_client_id))
    //        .requestEmail()
   //         .build();
   //     mGoogleSignInClient = GoogleSignIn.getClient(getActivity(), googleSignInOptions);
	//	mOut.setOnClickListener(new OnClickListener(){

			//	private Context context;

		//		@Override
			//	public void onClick(View p1) {
			//		final ProgressDialog dialog; 
			//		dialog = new ProgressDialog(getActivity()); 
				//	dialog.setMessage("กำลังออกจากระบบ");
				//	dialog.setCancelable(true); 
				//	dialog.setCanceledOnTouchOutside(true); 
				//	dialog.show(); 
			//		new Handler().postDelayed(new Runnable() {
		//					public void run() {
		//	//					dialog.dismiss();
		//						FirebaseAuth.getInstance().signOut();
			//					mSign.setVisibility(View.VISIBLE);
		//						mOut.setVisibility(View.GONE);		
			//					username.setText("--");
			//					edit_profile.setVisibility(View.GONE);
			//					Glide.with(getActivity()).load("https://moonlightvpn-th.tk/profile_noload.png").into(profile);	
					//			Toast.makeText(getActivity(), "ออกจกระบบแล้ว" , Toast.LENGTH_LONG).show();
								
			//				}
				//		}, 1000);
				
					
				//}
	//	});
		
       // mSign.setOnClickListener(new OnClickListener(){
//
				//@Override
				////public void onClick(View p1) {
	//				try{
			//			Intent choose = mGoogleSignInClient.getSignInIntent();
	//					startActivityForResult(choose, RC_SIGN_IN);
	////				} catch (Exception e){
			//			Toast.makeText(getActivity(), e.toString(), Toast.LENGTH_LONG).show();
		//	/		}
	//				
	//			}
	//	});
		
	//	FirebaseUser userData = FirebaseAuth.getInstance().getCurrentUser();
	//	if ((FirebaseAuth.getInstance().getCurrentUser() != null)) {
		//	username.setText(userData.getDisplayName());
	//		//Glide library check libs/glide.jar
	//		Glide.with(getActivity()).load(userData.getPhotoUrl().toString()).into(profile);	
	//	}
	//	FirebaseUser currentUser = mAuth.getCurrentUser();
   //     if (currentUser != null) {
  //          mSign.setVisibility(View.GONE);
		//	mOut.setVisibility(View.VISIBLE);
		//	edit_profile.setVisibility(View.VISIBLE);
		//	
    //    }else{
	///		mSign.setVisibility(View.VISIBLE);
			//edit_profile.setVisibility(View.GONE);
		//	
		//	mOut.setVisibility(View.GONE);
	//	}
		
   //     return view;
 ///   }
	
	//@Override
  //  public void onActivityResult(int requestCode, int resultCode, Intent data) {
   //     super.onActivityResult(requestCode, resultCode, data);
  //      if (requestCode == RC_SIGN_IN) {
    //        Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
       //     try {
                // Google Sign In was successful, authenticate with Firebase
        //        GoogleSignInAccount account = task.getResult(ApiException.class);
               // firebaseAuthWithGoogle(account.getIdToken());
				
       ////     } catch (ApiException e) {
                // Google Sign In failed
     //           Toast.makeText(getActivity(),"เข้าสู่ระบบผิดพลาด", Toast.LENGTH_LONG).show();
				
    //      }
  ///     // }
   // }
//
	
    //auth user with firebase
  //  private void firebaseAuthWithGoogle(String idToken) {
		//final ProgressDialog dialog; 
      //  dialog = new ProgressDialog(getActivity()); 
     //   dialog.setMessage("กำลังเข้าสู่ระบบ");
     //   dialog.setCancelable(true); 
      //  dialog.setCanceledOnTouchOutside(true); 
    //    dialog.show(); 
     //   AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
     //   mAuth.signInWithCredential(credential)
           // .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
               // @Override
              //  public void onComplete(@NonNull Task<AuthResult> task) {
                 //   if (task.isSuccessful()) {
                   //     dialog.dismiss();
                    //    mSign.setVisibility(View.GONE);
					//	mOut.setVisibility(View.VISIBLE);
						
					////	FirebaseUser userData = FirebaseAuth.getInstance().getCurrentUser();
						//Toast.makeText(getActivity(), "ยินดีต้อนรับคุณ " + userData.getDisplayName(), Toast.LENGTH_LONG).show();
					//	
					//	if ((FirebaseAuth.getInstance().getCurrentUser() != null)) {
							//username.setText(userData.getDisplayName());
							////Glide library check libs/glide.jar
							//edit_profile.setVisibility(View.VISIBLE);
							
							///Glide.with(getActivity()).load(userData.getPhotoUrl().toString()).into(profile);

						//}else{
							//edit_profile.setVisibility(View.GONE);
							//
							//mSign.setVisibility(View.GONE);
							//mOut.setVisibility(View.VISIBLE);
					//	}
                //    } else {
						//dialog.dismiss();
					//	mSign.setVisibility(View.VISIBLE);
					///	mOut.setVisibility(View.GONE);
                        // If sign in fails, display a message to the user.
                       // Toast.makeText(getActivity(), "เข้าสู่ระบบผิดพลาด", Toast.LENGTH_LONG).show();
                  //  }
              //  }
			
           // });
			//}
			
return view;
}


	public static profile getInstance() {
        return instance;
    }

    public void proMethod() {
		
		}
}
