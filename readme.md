
# publish to GCS

1. In GCP, add bucket `github-cb-repo` with a folder `maven2`
2. Next, create a service account, `gradle-gcs-maven2`.  Role `Storage Object Admin`. Create JSON key file.
3. Update bash profile; `export GOOGLE_APPLICATION_CREDENTIALS=/Users/credentials/file-name.json`
4. Now `./gradlew publish` works.  (make sure gradle daemon is restarted)