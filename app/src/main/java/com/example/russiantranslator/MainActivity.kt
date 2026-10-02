package com.example.russiantranslator

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.widget.Button
import android.widget.Switch
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import android.content.pm.PackageManager
import android.Manifest
import androidx.core.app.ActivityCompat

class MainActivity : AppCompatActivity() {
    private lateinit var serviceToggle: Switch
    private lateinit var statusTextView: TextView
    private lateinit var permissionsTextView: TextView
    private lateinit var languagesTextView: TextView

    private val permissions = arrayOf(
        Manifest.permission.INTERNET,
        Manifest.permission.ACCESS_NETWORK_STATE,
        Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.WRITE_EXTERNAL_STORAGE,
        Manifest.permission.CAMERA
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        serviceToggle = findViewById(R.id.serviceToggle)
        statusTextView = findViewById(R.id.statusTextView)
        permissionsTextView = findViewById(R.id.permissionsTextView)
        languagesTextView = findViewById(R.id.languagesTextView)

        requestPermissionsIfNeeded()
        updateUi()

        serviceToggle.setOnCheckedChangeListener { _, isChecked ->
            if (!isAccessibilityEnabled()) {
                serviceToggle.isChecked = false
                startActivity(Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS))
                return@setOnCheckedChangeListener
            }

            TranslatorAccessibilityService.setEnabled(this, isChecked)
            updateUi()
        }

        findViewById<Button>(R.id.enableAccessibilityButton).setOnClickListener {
            startActivity(Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS))
        }
    }

    override fun onResume() {
        super.onResume()
        updateUi()
    }

    private fun requestPermissionsIfNeeded() {
        val missing = permissions.filter {
            ContextCompat.checkSelfPermission(this, it) != PackageManager.PERMISSION_GRANTED
        }.toTypedArray()

        if (missing.isNotEmpty()) {
            ActivityCompat.requestPermissions(this, missing, 1001)
        }
    }

    private fun updateUi() {
        val enabled = isAccessibilityEnabled() && TranslatorAccessibilityService.isEnabled(this)
        serviceToggle.isChecked = enabled
        statusTextView.text = if (enabled) "Перевод активен" else "Перевод выключен"

        val statuses = permissions.joinToString("\n") { permission ->
            val name = permission.substringAfterLast('.')
            val granted = ContextCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED
            val mark = if (granted) "✓" else "✗"
            "$mark $name"
        }
        permissionsTextView.text = "Разрешения:\n$statuses"

        languagesTextView.text = "Поддерживаемые языки: ${MultiLanguageTranslationDictionary.getAvailableLanguages().joinToString(", ")}"
    }

    private fun isAccessibilityEnabled(): Boolean {
        val enabledServices = Settings.Secure.getString(
            contentResolver,
            Settings.Secure.ENABLED_ACCESSIBILITY_SERVICES
        ) ?: return false

        return enabledServices.contains("com.example.russiantranslator/.TranslatorAccessibilityService")
    }
}
