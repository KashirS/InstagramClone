package cat.itb.instagramclone.fragments;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import cat.itb.instagramclone.R;
import cat.itb.instagramclone.activities.MainActivity;
import cat.itb.instagramclone.models.Publication;

import static android.app.Activity.RESULT_OK;

public class UploadImage extends Fragment {

    MaterialButton uploadButton;
    ImageView picToUpload;
    private Uri imageUri;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_upload_image, container, false);
        uploadButton = v.findViewById(R.id.uploadimage);
        picToUpload = v.findViewById(R.id.pic_to_upload);

        MainActivity.databaseReference = MainActivity.database.getReference("image");
        MainActivity.storageReference = FirebaseStorage.getInstance().getReference();

        picToUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(getView()).navigate(R.id.action_uploadImage_to_gallray);
            }
        });

        uploadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (imageUri != null){
                    uploadToFirebase(imageUri);
                }else {
                    Toast.makeText(getContext(),"Please select image",Toast.LENGTH_LONG).show();
                }
            }
        });

        return v;
    }

    private void uploadToFirebase(Uri imageUri) {

        StorageReference fileRef = MainActivity.storageReference.child(System.currentTimeMillis() + "."+ getFileExtention(imageUri));
        fileRef.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                fileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {

                        Publication publication = new Publication(uri.toString());
                        String pubId = MainActivity.databaseReference.push().getKey();
                        MainActivity.databaseReference.child(pubId).setValue(publication);
                        Toast.makeText(getContext(),"Uploaded successfully", Toast.LENGTH_LONG).show();
                    }
                });
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getContext(),"Uploading failed", Toast.LENGTH_LONG).show();
            }
        });


    }

    private String getFileExtention(Uri imageUri) {
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(getActivity().getContentResolver().getType(imageUri));
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (data != null && resultCode == RESULT_OK){
            imageUri = data.getData();
            picToUpload.setImageURI(imageUri);
        }

    }
}