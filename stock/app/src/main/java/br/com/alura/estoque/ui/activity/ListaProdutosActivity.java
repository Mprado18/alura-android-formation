package br.com.alura.estoque.ui.activity;

import static br.com.alura.estoque.ui.constants.Constants.MENSAGEM_ERRO_AO_CARREGAR_PRODUTOS;
import static br.com.alura.estoque.ui.constants.Constants.MENSAGEM_ERRO_AO_EDITAR_PRODUTOS;
import static br.com.alura.estoque.ui.constants.Constants.MENSAGEM_ERRO_AO_REMOVER_PRODUTOS;
import static br.com.alura.estoque.ui.constants.Constants.MENSAGEM_ERRO_AO_SALVAR_PRODUTOS;
import static br.com.alura.estoque.ui.constants.Constants.TITULO_APPBAR;

import android.os.Bundle;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import br.com.alura.estoque.R;
import br.com.alura.estoque.model.Produto;
import br.com.alura.estoque.repository.ProdutosRepository;
import br.com.alura.estoque.ui.dialog.EditaProdutoDialog;
import br.com.alura.estoque.ui.dialog.SalvaProdutoDialog;
import br.com.alura.estoque.ui.adapter.ListaProdutosAdapter;

public class ListaProdutosActivity extends AppCompatActivity {

    private ListaProdutosAdapter adapter;
    private ProdutosRepository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_produtos);
        setTitle(TITULO_APPBAR);

        configuraListaProdutos();
        configuraFabSalvaProduto();

        repository = new ProdutosRepository(this);
        buscaProdutos();
    }

    private void buscaProdutos() {
        repository.buscaProdutos(new ProdutosRepository.DadosCarregadosCallback<List<Produto>>() {
            @Override
            public void quandoSucesso(List<Produto> produtosNovos) {
                adapter.atualiza(produtosNovos);
            }

            @Override
            public void quandoFalha(String erro) {
                mostraErro(MENSAGEM_ERRO_AO_CARREGAR_PRODUTOS);
            }
        });
    }

    private void mostraErro(String mensagem) {
        Toast.makeText(this, mensagem, Toast.LENGTH_SHORT).show();
    }

    private void configuraListaProdutos() {
        RecyclerView listaProdutos = findViewById(R.id.activity_lista_produtos_lista);
        adapter = new ListaProdutosAdapter(this, this::abreFormularioEditaProduto);
        listaProdutos.setAdapter(adapter);

        adapter.setOnItemClickRemoveContextMenuListener(
                (posicao, produtoEscolhido) -> {
                    repository.remove(produtoEscolhido,
                            new ProdutosRepository.DadosCarregadosCallback<Void>() {
                                @Override
                                public void quandoSucesso(Void resultado) {
                                    adapter.remove(posicao);
                                }

                                @Override
                                public void quandoFalha(String erro) {
                                    mostraErro(MENSAGEM_ERRO_AO_REMOVER_PRODUTOS);
                                }
                            });
                });
    }

    private void configuraFabSalvaProduto() {
        FloatingActionButton fabAdicionaProduto =
                findViewById(R.id.activity_lista_produtos_fab_adiciona_produto);
        fabAdicionaProduto.setOnClickListener(v -> abreFormularioSalvaProduto());
    }

    private void abreFormularioSalvaProduto() {
        new SalvaProdutoDialog(this, produtoCriado ->
                repository.salva(produtoCriado,
                        new ProdutosRepository.DadosCarregadosCallback<Produto>() {
                            @Override
                            public void quandoSucesso(Produto produtoSalvo) {
                                adapter.adiciona(produtoSalvo);
                            }

                            @Override
                            public void quandoFalha(String erro) {
                                mostraErro(MENSAGEM_ERRO_AO_SALVAR_PRODUTOS);
                            }
                        })
        ).mostra();
    }

    private void abreFormularioEditaProduto(int posicao, Produto produto) {
        new EditaProdutoDialog(this, produto,
                produtoCriado -> repository.edita(produtoCriado,
                        new ProdutosRepository.DadosCarregadosCallback<Produto>() {
                            @Override
                            public void quandoSucesso(Produto produtoEditado) {
                                adapter.edita(posicao, produtoEditado);
                            }

                            @Override
                            public void quandoFalha(String erro) {
                                mostraErro(MENSAGEM_ERRO_AO_EDITAR_PRODUTOS);
                            }
                        })
        ).mostra();
    }

}
