package com.braintreepayments.api.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * {@link com.braintreepayments.api.models.PaymentMethod} representing a Coinbase account.
 * @see {@link com.braintreepayments.api.models.PayPalAccount}
 * @see {@link com.braintreepayments.api.models.PaymentMethod}
 */
public class CoinbaseAccount extends PaymentMethod implements Parcelable, Serializable {

    protected static final String PAYMENT_METHOD_TYPE = "CoinbaseAccount";

    @SerializedName("code")
    private String mAccessCode;

    @SerializedName("details")
    private CoinbaseAccountDetails mDetails;

    public CoinbaseAccount() {}

    protected void setAccessCode(String accessCode) {
        mAccessCode = accessCode;
    }

    /**
     * @return The email address associated with this Coinbase account.
     */
    public String getEmail() {
        if (mDetails != null) {
            return mDetails.getEmail();
        } else {
            return "";
        }
    }

    /**
     * @return The type of this {@link com.braintreepayments.api.models.PaymentMethod} (always "Coinbase").
     */
    @Override
    public String getTypeLabel() {
        return "Coinbase";
    }

    /**
     * Required for and handled by {@link com.braintreepayments.api.Braintree}. Not intended for general consumption.
     *
     * @param json Raw JSON representation of a {@link com.braintreepayments.api.models.CoinbaseAccount}.
     * @return {@link com.braintreepayments.api.models.CoinbaseAccount} for use in payment method selection UIs.
     */
    public static CoinbaseAccount fromJson(String json) {
        return new Gson().fromJson(json, CoinbaseAccount.class);
    }

    @Override
    public int describeContents() { return 0; }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.mAccessCode);
        dest.writeParcelable(this.mDetails, 0);
        dest.writeString(this.nonce);
        dest.writeString(this.description);
        dest.writeSerializable(this.options);
        dest.writeString(this.mSource);
    }

    private CoinbaseAccount(Parcel in) {
        this.mAccessCode = in.readString();
        this.mDetails = in.readParcelable(CoinbaseAccountDetails.class.getClassLoader());
        this.nonce = in.readString();
        this.description = in.readString();
        this.options = (PaymentMethodOptions) in.readSerializable();
        this.mSource = in.readString();
    }

    public static final Creator<CoinbaseAccount> CREATOR = new Creator<CoinbaseAccount>() {
        public CoinbaseAccount createFromParcel(Parcel source) {return new CoinbaseAccount(source);}

        public CoinbaseAccount[] newArray(int size) {return new CoinbaseAccount[size];}
    };

    private static class CoinbaseAccountDetails implements Parcelable, Serializable {
        @SerializedName("email")
        private String mEmail;

        public CoinbaseAccountDetails() {}

        public String getEmail() {
            return mEmail;
        }

        @Override
        public int describeContents() { return 0; }

        @Override
        public void writeToParcel(Parcel dest, int flags) {dest.writeString(this.mEmail);}

        private CoinbaseAccountDetails(Parcel in) {this.mEmail = in.readString();}

        public static final Creator<CoinbaseAccountDetails> CREATOR =
                new Creator<CoinbaseAccountDetails>() {
                    public CoinbaseAccountDetails createFromParcel(
                            Parcel source) {return new CoinbaseAccountDetails(source);}

                    public CoinbaseAccountDetails[] newArray(
                            int size) {return new CoinbaseAccountDetails[size];}
                };
    }
}
