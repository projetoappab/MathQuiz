package br.com.profpabllo.mathquiz.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import br.com.profpabllo.mathquiz.R;
import br.com.profpabllo.mathquiz.model.AnalisadorQuestao;
import br.com.profpabllo.mathquiz.model.Questao;
import br.com.profpabllo.mathquiz.model.QuestaoRepositorio;

public class QuizActivity extends AppCompatActivity {

    private QuestaoRepositorio repositorio = new QuestaoRepositorio();
    private int indice_questao = 0;
    private TextView textViewTextoPergunta;
    private Button botaoResposta1;
    private Button botaoResposta2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz); //A classe R é uma classe de recursos gerada pelo Android

        Questao questao = repositorio.getListaQuestoes().get(0); //Obtendo a questão pelo indice do List

        textViewTextoPergunta = findViewById(R.id.texto_pergunta_textview); //Método findViewById para buscar um view pelo id
        textViewTextoPergunta.setText(questao.getTexto());

        /*
        * Button botaoResposta1 = findViewById(R.id.button);
        * botaoResposta1.setText(String.valueOf(questao.getRespostaCorreta()));
        * Para renomear um ID, posso usar a metodologia tradicional ou Selecionar o ID, Shift + F6, automaticamente já é alterado no xml
        * */

        /*
        * Um objeto Listner é implementado por interfaces (<- View) e serve para ouvir/aguardar um elemento, neste caso um botão
        * */

        View.OnClickListener listener = new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                final String resposta = ((Button)view).getText().toString();
                String mensagem;

                AnalisadorQuestao analisadorQuestao = new AnalisadorQuestao();
                Questao questao = repositorio.getListaQuestoes().get(indice_questao);

                if (analisadorQuestao.isRespostaCorreta(questao, Double.parseDouble(resposta))){
                    mensagem = "Parabéns, resposta correta!";
                } else {
                    mensagem = "Aah, resposta incorreta!";
                }

                /*
                * Notificações no Android são criadas com a classe Toast
                * */
                Toast.makeText(QuizActivity.this, mensagem, Toast.LENGTH_SHORT).show();

            }
        };

        botaoResposta1 = findViewById(R.id.opcao1_button);
        botaoResposta1.setText(String.valueOf(questao.getRespostaCorreta()));
        botaoResposta1.setOnClickListener(listener);

        botaoResposta2 = findViewById(R.id.opcao2_button);
        botaoResposta2.setText(String.valueOf(questao.getRespostaIncorreta()));
        botaoResposta2.setOnClickListener(listener);

        View.OnClickListener listenerProximaPergunta = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                indice_questao++;

                if(indice_questao >= repositorio.getListaQuestoes().size()){
                    indice_questao = 0;
                }

                /*Método removido para evitar repetição de código
                * Questao questao = repositorio.getListaQuestoes().get(indice_questao);
                *
                * textViewTextoPergunta.setText(questao.getTexto());
                * botaoResposta1.setText(String.valueOf(questao.getRespostaCorreta()));
                * botaoResposta2.setText(String.valueOf(questao.getRespostaIncorreta()));
                */

                exibirQuestao(indice_questao);

            }
        };

        Button botaoProximaPergunta = findViewById(R.id.proxima_pergunta_button);

        /*Método removido para evitar repetição de código -> incluído no arquivo de layout activity_quiz
        * botaoProximaPergunta.setText("Próxima pergunta...");
        */

        botaoProximaPergunta.setOnClickListener(listenerProximaPergunta);

        exibirQuestao(indice_questao);

    }

    //Método criado para evitar repetição de código
    public void exibirQuestao(final int indice_questao){
        Questao questao = repositorio.getListaQuestoes().get(indice_questao);
        textViewTextoPergunta.setText(questao.getTexto());
        botaoResposta1.setText(String.valueOf(questao.getRespostaCorreta()));
        botaoResposta2.setText(String.valueOf(questao.getRespostaIncorreta()));

    }

}
