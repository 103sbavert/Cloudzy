package com.dbtechprojects.cloudstatustest.util

object Constants {
    const val PENDING_INTENT_REQUEST_CODE = 5
    const val AWS_INTENT_ACTION = "com.dbtechprojects.cloudstatustest.OPEN_AWS_FRAGMENT"
    const val GCP_INTENT_ACTION = "com.dbtechprojects.cloudstatustest.OPEN_GCP_FRAGMENT"
    const val AWS_NOTIFICATION_CHANNEL_ID = "AWS_NOTIF"
    const val GCP_NOTIFICATION_CHANNEL_ID = "GCP_NOTIF"
    const val AWS_WORK_REQUEST_UNIQUE_ID = "AWS_WORK"
    const val GCP_WORK_REQUEST_UNIQUE_ID = "GCP_WORK"
    const val AWS_URL = "https://status.aws.amazon.com"
    const val AZURE_URL = "https://azurestatuscdn.azureedge.net"
    const val GCP_URL = "https://status.cloud.google.com"
}
