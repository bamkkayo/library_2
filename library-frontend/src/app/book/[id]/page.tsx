import DeleteButton from "./DeleteButton";

async function getBook(id: string) {
    const res = await fetch(`${process.env.NEXT_PUBLIC_API_URL}/api/books/${id}`, {
        cache: "no-store",
    });
    if (!res.ok) return null;
    return res.json();
}

export default async function BookDetailPage({ params }: { params: { id: string } }) {
    const book = await getBook(params.id);

    if (!book) {
        return <div className="text-center py-20">도서를 찾을 수 없습니다.</div>;
    }

    return (
        <div className="max-w-2xl mx-auto">
            <div className="bg-white rounded-3xl border border-slate-200 overflow-hidden shadow-sm">
                <div className="bg-slate-900 p-10 text-center">
                    <span className="text-4xl">📖</span>
                    <h1 className="text-3xl font-bold text-white mt-4">{book.title}</h1>
                </div>
                <div className="p-8 space-y-6">
                    <div className="grid grid-cols-2 gap-4">
                        <div className="bg-slate-50 p-4 rounded-2xl">
                            <p className="text-xs text-slate-400 font-bold uppercase">Author</p>
                            <p className="text-lg font-bold text-slate-800">{book.author}</p>
                        </div>
                        <div className="bg-slate-50 p-4 rounded-2xl">
                            <p className="text-xs text-slate-400 font-bold uppercase">Price</p>
                            <p className="text-lg font-bold text-slate-800">{book.price.toLocaleString()}원</p>
                        </div>
                    </div>

                    <div className={`p-4 rounded-2xl text-center font-bold ${book.available ? 'bg-green-50 text-green-600' : 'bg-red-50 text-red-600'}`}>
                        {book.available ? "현재 대출 가능" : "대출 중"}
                    </div>

                    <div className="flex gap-3 mt-8">
                        <a href="/" className="flex-1 text-center py-4 bg-slate-100 text-slate-600 font-bold rounded-2xl hover:bg-slate-200 transition">
                            목록으로
                        </a>
                        <DeleteButton id={book.id} />
                    </div>
                </div>
            </div>
        </div>
    );
}