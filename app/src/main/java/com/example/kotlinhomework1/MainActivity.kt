package com.example.kotlinhomework1

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val ed_name = findViewById<EditText>(R.id.ed_name)
        val tv_text = findViewById<TextView>(R.id.tv_text)
        val radioGroup = findViewById<RadioGroup>(R.id.radioGroup)
        val btn_mora = findViewById<Button>(R.id.btn_mora)
        val tv_name = findViewById<TextView>(R.id.tv_name)
        val tv_winner = findViewById<TextView>(R.id.tv_winner)
        val tv_mmora = findViewById<TextView>(R.id.tv_mmora)
        val tv_cmora = findViewById<TextView>(R.id.tv_cmora)

        val mora = mapOf(0 to "剪刀", 1 to "石頭", 2 to "布")

        btn_mora.setOnClickListener {
            if (ed_name.text.isEmpty()) {
                tv_text.text = "請輸入玩家姓名"
                return@setOnClickListener
            }

            // 玩家出拳
            val myMora = when (radioGroup.checkedRadioButtonId) {
                R.id.btn_scissor -> 0
                R.id.btn_stone -> 1
                else -> 2
            }

            // 電腦出拳
            val targetMora = (0..2).random()

            // 存結果
            tv_name.text = "名字\n${ed_name.text}"
            tv_mmora.text = "我方出拳\n${mora[myMora]}"
            tv_cmora.text = "電腦出拳\n${mora[targetMora]}"

            // 結果出爐
            if (myMora == 0 && targetMora == 2 || myMora == 1 && targetMora == 0 || myMora == 2 && targetMora == 1) {
                tv_text.text = "恭喜您獲勝了！！！"
                tv_winner.text = "勝利者\n${ed_name.text}"
            } else if (myMora == targetMora) {
                tv_text.text = "平局，請再試一次！"
                tv_winner.text = "勝利者\n平手"
            } else {
                tv_text.text = "可惜，電腦獲勝了！"
                tv_winner.text = "勝利者\n電腦"
            }
        }
    }
}