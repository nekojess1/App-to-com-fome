package br.ufrpe.tocomfome.usuario.gui;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import br.ufrpe.tocomfome.R;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>  {
    private ArrayList<String> mNome = new ArrayList<>();
    private ArrayList<String> mDescricao = new ArrayList<>();
    private ArrayList<Bitmap> mFotos = new ArrayList<>();
    private Context mContext;

    public RecyclerViewAdapter(Context context, ArrayList<String> nome, ArrayList<String> descricao, ArrayList<Bitmap> foto) {
        this.mNome = nome;
        this.mDescricao = descricao;
        this.mContext = context;
        this.mFotos = foto;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.holder_layout, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.nome.setText(mNome.get(position));
        holder.estado.setText(mDescricao.get((position)));
        if (mFotos.get(position) != null){

            holder.imagem.setImageBitmap(mFotos.get(position));
        }


//        holder.btnVerMais.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(mContext, PerfilProfissional.class);
//                intent.putExtra("nome", mNome.get(position));
//                intent.putExtra("endereco", mCidade.get(position));
//                intent.putExtra("telefone", mTelefone.get(position));
//                intent.putExtra("email", mEmail.get(position));
//                intent.putExtra("cidade", mDescricao.get(position));
//                mContext.startActivity(intent);
//            }
//
//        });
    }

    @Override
    public int getItemCount() {
        return mNome.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView nome;
        ImageView imagem;
        RelativeLayout parentLayout;
        TextView estado;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nome = itemView.findViewById(R.id.nome);
            parentLayout = itemView.findViewById(R.id.parent_layout);
            estado = itemView.findViewById(R.id.descricao);
            imagem = itemView.findViewById(R.id.image);
        }
    }
}

