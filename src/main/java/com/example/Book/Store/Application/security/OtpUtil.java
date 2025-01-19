package com.example.Book.Store.Application.security;

import org.springframework.stereotype.Component;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Component
public class OtpUtil {
    private static final String SECRET_KEY = "your-secret-key"; // Replace with a securely generated key.
    private static final int OTP_VALIDITY_MINUTES = 5;

    public static String generateOtp() {
        try {
            // Get the current timestamp in 5-minute intervals
            long timeInterval = Instant.now().truncatedTo(ChronoUnit.MINUTES).getEpochSecond() / (OTP_VALIDITY_MINUTES * 60);

            // Generate HMAC-SHA256 hash using the secret key and time interval
            byte[] key = SECRET_KEY.getBytes();
            SecretKeySpec keySpec = new SecretKeySpec(key, "HmacSHA256");
            Mac mac = Mac.getInstance("HmacSHA256");
            mac.init(keySpec);

            byte[] hash = mac.doFinal(String.valueOf(timeInterval).getBytes());

            // Extract a 6-digit OTP
            int offset = hash[hash.length - 1] & 0xf; // Dynamic truncation
            int binary = ((hash[offset] & 0x7f) << 24) |
                    ((hash[offset + 1] & 0xff) << 16) |
                    ((hash[offset + 2] & 0xff) << 8) |
                    (hash[offset + 3] & 0xff);
            int otp = binary % 1_000_000; // Keep it within 6 digits

            return String.format("%06d", otp); // Pad with leading zeros if necessary
        } catch (Exception e) {
            throw new RuntimeException("Failed to generate OTP", e);
        }
    }
}
