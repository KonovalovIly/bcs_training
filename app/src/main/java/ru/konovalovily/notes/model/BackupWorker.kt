package ru.konovalovily.notes.model

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import kotlinx.coroutines.flow.firstOrNull
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class BackupWorker(
    context: Context,
    workerParameters: WorkerParameters
) : CoroutineWorker(context, workerParameters), KoinComponent {

    private val getAllNotesFlowUseCase by inject<GetAllNotesFlowUseCase>()

    override suspend fun doWork(): Result =
        when (val notes = getAllNotesFlowUseCase().firstOrNull()) {
            null -> {
                Log.d(TAG_LOG, "nothing to save")
                Result.failure()
            }
            else -> {
                Log.d(TAG_LOG, "saved ${notes.size} notes")
                Result.success()
            }
        }

    companion object {
        private const val TAG_LOG = "TAG"
    }
}