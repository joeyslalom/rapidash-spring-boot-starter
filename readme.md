
# Manual publish to GCS

1. In GCP, add bucket `github-cb-repo` with a folder `maven2`
2. Next, create a service account, `gradle-gcs-maven2`.  Role `Storage Object Admin`. Create JSON key file.
3. Update bash profile; `export GOOGLE_APPLICATION_CREDENTIALS=/Users/credentials/file-name.json`
4. Now `./gradlew publish` works.  (make sure gradle daemon is restarted)

# Using the library

* Each commit will update artifact version using `git describe`
* This integrates well with GitHub releases with `v0.0.1` style tags.