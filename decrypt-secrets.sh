#!/usr/bin/env bash
# Decrypt secret files that are checked in to Git using a passphrase provided as
# an environment variable. Can be used locally or from Continuous Deployment.

# If any one command fails, immediately exit.
# Treat unset variables as an error, and immediately exit.
# Write each command to stdout before executing.
set -eux

for GPG_FILE in *.gpg; do
  ORIGINAL_FILE="${GPG_FILE/.gpg/}"
  echo $ORIGINAL_FILE
  echo $GPG_FILE
  gpg --quiet --batch --yes --decrypt \
      --passphrase="$GPG_PASSPHRASE" \
      --output "$ORIGINAL_FILE" \
      "$GPG_FILE"
done
