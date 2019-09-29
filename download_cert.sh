openssl s_client -showcerts -connect mcloud:443 </dev/null 2>/dev/null|openssl x509 -outform PEM >mycertfile.pem
keytool -genkey -alias mcloud -keystore ./mcloud.ks -storepass 123456 -storetype BKS -provider org.bouncycastle.jce.provider.BouncyCastleProvider
keytool -import -storetype BKS -keystore ./mcloud.ks -file mycertfile.pem
