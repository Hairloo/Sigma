//package edu.mirea.hairloo1x3.sigma.ui.main.task_menu.recyclerviewtasks.firebase;
//
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//
//import com.firebase.ui.database.FirebaseRecyclerAdapter;
//import com.firebase.ui.database.FirebaseRecyclerOptions;
//
//import edu.mirea.hairloo1x3.sigma.R;
//
//public class TestListTasksAdapter  extends FirebaseRecyclerAdapter<Model, TestListTasksAdapter.ThisViewHolder> {
//    /**
//     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
//     * {@link FirebaseRecyclerOptions} for configuration options.
//     *
//     * @param options
//     */
//    public TestListTasksAdapter(@NonNull FirebaseRecyclerOptions<Model> options) {
//        super(options);
//    }
//
//    @Override
//    protected void onBindViewHolder(@NonNull ThisViewHolder holder, int position, @NonNull Model model) {
//        holder.name.setText(model.getName());
//        holder.dif.setText(model.getDif());
//        holder.status.setText("C");
//    }
//
//    @NonNull
//    @Override
//    public ThisViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item_task, parent, false);
//        return new ThisViewHolder(view);
//    }
//
//    class ThisViewHolder extends RecyclerView.ViewHolder{
//        TextView name, dif, status;
//
//        public ThisViewHolder(@NonNull View itemView){
//            super(itemView);
//            name = itemView.findViewById(R.id.task_name);
//            status = itemView.findViewById(R.id.task_status);
//            dif = itemView.findViewById(R.id.task_dif);
//        }
//    }
//}
