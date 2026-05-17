import Link from "next/link";

export default function BookCard({ id, title, author, price, available }: any) {
    return (
        <Link href={`/books/${id}`} className="group bg-white border border-slate-200 p-5 rounded-3xl shadow-sm hover:shadow-md hover:border-blue-300 transition-all">
            <div className="w-12 h-12 bg-slate-50 rounded-2xl flex items-center justify-center text-xl mb-4 group-hover:bg-blue-50 transition">
                📚
            </div>
            <h3 className="font-bold text-slate-900 text-lg truncate">{title}</h3>
            <p className="text-slate-400 text-sm mb-4">{author}</p>
            <div className="flex justify-between items-center pt-4 border-t border-slate-50">
                <span className="font-bold text-blue-600">{price.toLocaleString()}원</span>
                <span className={`text-xs px-2 py-1 rounded-lg font-bold ${available ? 'bg-green-100 text-green-700' : 'bg-red-100 text-red-700'}`}>
          {available ? '대출가능' : '대출중'}
        </span>
            </div>
        </Link>
    );
}