package com.example.mypracapplication.presentation.ui.fragment;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.Settings;
import android.view.View;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import com.example.mypracapplication.R;
import com.google.android.material.snackbar.Snackbar;

public abstract class PermissionFragment extends Fragment {

    /**
     * Default Permission Request Code.
     */
    protected static final int DEFAULT_PERMISSION_REQUEST = 142;


    /**
     * Takes an array of permissions and checks if the user allowed all of them.
     *
     * @param permissions Array of permissions to check
     * @return True if user allowed all permissions, false otherwise
     */
    protected final boolean checkPermissions(String... permissions) {
        if (getActivity() != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(getActivity(), permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    /**
     * Takes an array of permissions and checks if the user allowed all of them.
     * If not, it automatically requests them with default request code.
     *
     * @param permissions Array of permissions to check and request if needed.
     * @return True if user allowed all permissions, false otherwise
     */
    protected final boolean checkAndRequestPermissions(String... permissions) {
        return checkAndRequestPermissions(DEFAULT_PERMISSION_REQUEST, permissions);
    }

    /**
     * Takes an array of permissions and checks if the user allowed all of them.
     * If not, it automatically requests them with custom request code.
     *
     * @param requestCode Request code that is used if permission is requested. MUST OVERRIDE requestPermissionResult() method if
     *                    request code is different than DEFAULT_PERMISSION_REQUEST.
     * @param permissions Array of permissions to check and request if needed
     * @return True if the user allowed all permissions, false otherwise
     */
    protected final boolean checkAndRequestPermissions(int requestCode, String... permissions) {
        if (checkPermissions(permissions)) {
            return true;
        } else {
            requestPermissions(permissions, requestCode);
            return false;
        }
    }


    /**
     * Simplified requestPermissions where you don't have to create new String[]{} but simply pass all permissions that you
     * want to check, and default request code is used.
     *
     * @param permissions Array of permissions that you want to request
     */
    protected void requestPermissions(String... permissions) {
        requestPermissions(permissions, DEFAULT_PERMISSION_REQUEST);
    }


    /**
     * Simplified requestPermissions where you don't have to create new String[]{} but simply pass all permissions that you
     * want to check.
     *
     * @param requestCode Request code used in onRequestPermissionResult. MUST OVERRIDE requestPermissionResult() method if
     *                    request code is different than DEFAULT_PERMISSION_REQUEST.
     * @param permissions Array of permissions that you want to request
     */
    protected void requestPermissions(int requestCode, String... permissions) {
        requestPermissions(permissions, requestCode);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == DEFAULT_PERMISSION_REQUEST) {
            defaultPermissionsResult(permissions, grantResults);
        } else {
            requestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    /**
     * Checks the result of permission requests and calls permissionsGranted() if all permissions are allowed, otherwise it
     * calls permissionDenied().
     */
    private void defaultPermissionsResult(String[] permissions, int[] grantResults) {
        if (checkPermissionResults(grantResults)) {
            permissionGranted();
        } else {
            permissionDenied(permissions, grantResults);
        }
    }

    /**
     * Called when all permissions are allowed. Must be implemented in a child.
     */
    protected abstract void permissionGranted();


    /**
     * Called when one of the permission is not allowed and shows Snackbar by default. Override this method to
     * implement custom logic. It also takes permissions and grantResults so that the developer can handle
     * specific permission denial.
     *
     * @param permissions  Array of asked permissions
     * @param grantResults Array of user responses
     */
    protected void permissionDenied(String[] permissions, int[] grantResults) {
        showSnackbar();
    }

    /**
     * Must override this method if using custom request code.
     *
     * @param requestCode  Custom request code to split the logic inside the method
     * @param permissions  Array of asked permissions
     * @param grantResults Array of user responses
     */
    protected void requestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        defaultPermissionsResult(permissions, grantResults);
    }

    /**
     * Checks if the user allowed all permissions that were requested.
     *
     * @param results Array of request results
     * @return True if thenuser allowed all permissions, false otherwise
     */
    protected final boolean checkPermissionResults(int[] results) {
        for (int result : results) {
            if (result != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    /**
     * Displays the default Snackbar with Action to open the Application Details in Settings so the user
     * can enable permissions.
     */
    protected void showSnackbar() {
        if (getActivity() != null) {
            Snackbar.make(getActivity().findViewById(android.R.id.content), R.string.app_name, Snackbar.LENGTH_LONG)
                    .setAction(R.string.app_name, new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            startActivity(getApplicationSettingsIntent());
                        }
                    }).show();
        }
    }

    /**
     * Creates an intent for the application details in settings.
     */
    protected final Intent getApplicationSettingsIntent() {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.setData(Uri.parse("package:" + getActivity().getPackageName()));
        return intent;
    }
}
